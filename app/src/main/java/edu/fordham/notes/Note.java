package edu.fordham.notes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    static void open(Context context, int id, boolean newNote) {
        Log.i("notesapp", "new note? " + newNote);
        Intent intent = new Intent(context, NoteEditorActivity.class);
        intent.putExtra("newNote", newNote);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
