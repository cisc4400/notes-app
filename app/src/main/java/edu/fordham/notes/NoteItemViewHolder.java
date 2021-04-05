package edu.fordham.notes;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NoteItemViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitleView;

    public NoteItemViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitleView = itemView.findViewById(R.id.noteTitleTextView);
    }

    public void updateView(NotesAdapter adapter, List<Note> notes, int position) {
        Note note = notes.get(position);
        noteTitleView.setText(note.title);
        noteTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(note.title);
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Note.open(v.getContext(), note.nid, false);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.noteDao.deleteNote(note);
                        adapter.notes.remove(note);
                        adapter.notifyItemRemoved(position);
                    }
                });
                builder.create().show();
            }
        });
    }
}
