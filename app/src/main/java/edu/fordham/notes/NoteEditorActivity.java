package edu.fordham.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NoteEditorActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText contentEditText;
    boolean isNewNote;
    int nid;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        titleEditText = findViewById(R.id.titleEditTex);
        contentEditText = findViewById(R.id.contentEditTex);

        NoteDatabase db = NoteDatabase.getDatabase(this);
        NoteDao dao = db.NoteDao();

        isNewNote = getIntent().getBooleanExtra("newNote", true);
        nid = getIntent().getIntExtra("id", -1);

        if (!isNewNote) {
            note = dao.findById(nid);
            titleEditText.setText(note.title);
            contentEditText.setText(note.content);
        } else {
            note = new Note();
        }

        Button saveBtn = findViewById(R.id.button);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(v.getContext(), "Title must not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                note.title = title;
                note.content = content;

                if (isNewNote) {
                    dao.insertNote(note);
                } else {
                    dao.updateNote(note);
                }
                finish();
            }
        });
    }
}