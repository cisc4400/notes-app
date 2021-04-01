package edu.fordham.notes;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteItemViewHolder> {
    List<Note> notes;
    NoteDao noteDao;

    public NotesAdapter(List<Note> data, NoteDao dao) {
        notes = data;
        noteDao = dao;
    }

    @NonNull
    @Override
    public NoteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new NoteItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteItemViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTitleView.setText(note.title);
        holder.noteTitleView.setOnClickListener(new View.OnClickListener() {
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
                        noteDao.deleteNote(note);
                        notes.remove(note);
                        notifyItemRemoved(position);
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
