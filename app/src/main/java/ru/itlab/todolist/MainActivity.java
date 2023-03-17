package ru.itlab.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private NoteAdapter notesAdapter;

    private MainActivityNewModel mainActivityNewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivityNewModel = new ViewModelProvider(this).get(MainActivityNewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floating);

        mainActivityNewModel.getNotes().observe(MainActivity.this, notes -> {
            notesAdapter.setData(notes);
        });

        recyclerView = findViewById(R.id.recycle);

        notesAdapter = new NoteAdapter();
        notesAdapter.setonClick(note -> {
            mainActivityNewModel.remove(note);
        });
        recyclerView.setAdapter(notesAdapter);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNewNoteActivity.class);
            startActivity(intent);
        });

    }
}