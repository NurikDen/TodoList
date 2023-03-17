package ru.itlab.todolist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {
    private List<Note> notes;
    private View.OnClickListener onNoteClickListener;
    private INoteClick noteClick;

    public NoteAdapter() {
        notes =new ArrayList<>();
    }

    public void setonClick(INoteClick noteClick){
        this.noteClick = noteClick;
    }

    public void setData(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note,
                parent,
                false
        );
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NotesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Note note = notes.get(position);
        holder.textViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               noteClick.onNoteClick(notes.get(position));
            }
        });
        holder.textViewNote.setText(note.text);
        switch (note.priority) {
            case 0:
                holder.textViewNote.setBackgroundColor(holder.textViewNote.getResources().getColor(R.color.green));
                break;
            case 1:
                holder.textViewNote.setBackgroundColor(holder.textViewNote.getResources().getColor(R.color.yellow));
                break;
            case 2:
                holder.textViewNote.setBackgroundColor(holder.textViewNote.getResources().getColor(R.color.red));
                break;
        }

    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNote;
        public NotesViewHolder(@NonNull View itemView ){
            super(itemView);
            textViewNote = itemView.findViewById(R.id.note);

        }
    }
    }

