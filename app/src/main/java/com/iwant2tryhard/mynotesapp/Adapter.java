package com.iwant2tryhard.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iwant2tryhard.mynotesapp.core.NoteObj;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<NoteObj> notes;

    public Adapter(Context context, List<NoteObj> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_notes_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTITLE();
        String date = notes.get(position).getDATE();
        String time = notes.get(position).getTIME();
        holder.noteTitleTextView.setText(title);
        holder.noteDateTextView.setText(date);
        holder.noteTimeTextView.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView noteTitleTextView, noteDateTextView, noteTimeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView);
            noteDateTextView = itemView.findViewById(R.id.noteDateTextView);
            noteTimeTextView = itemView.findViewById(R.id.noteTimeTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), NoteView.class);
                    Log.d("adpr-getAdapterPosition", String.valueOf(getAdapterPosition()));
                    i.putExtra("ID", getAdapterPosition());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
