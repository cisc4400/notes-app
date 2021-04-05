package edu.fordham.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
        holder.updateView(this, notes, position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
