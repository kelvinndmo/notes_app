package com.example.tozza;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list_1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        // reference it inside the classs
        final ListView listNotes = findViewById(R.id.list_notes);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        ArrayAdapter<NoteInfo> adapterNotes =
                new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listNotes.setAdapter(adapterNotes);

        // control and space to show the stuff...
        //* this is a nested class who's instances exists inside the instances of notelist activirt
        //* class
        //*
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //control + P
                // Qualify the NoteListActivity
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);
                startActivity(intent);

            }
        });
    }

}
