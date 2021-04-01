package edu.fordham.notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NoteItemViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitleView;

    public NoteItemViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitleView = itemView.findViewById(R.id.noteTitleTextView);
    }
}
