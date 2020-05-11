package com.startng.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.startng.dbs.Note;
import com.startng.dbs.NoteContract;
import com.startng.dbs.NoteDbHelper;

import java.util.ArrayList;

public class HeadlinesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HeadlinesAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private NoteDbHelper mHelper;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mHelper = new NoteDbHelper(this);
        updateUI();
    }

    private void updateUI() {
        ArrayList<Note> NoteItems = new ArrayList<>();
        Log.d("HEadr","Update function");
        Double total_price = 0.0;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(NoteContract.NoteEntry.TABLE,
                new String[]{NoteContract.NoteEntry._ID, NoteContract.NoteEntry.COL_NOTE_BODY,NoteContract.NoteEntry.COL_NOTE_CREATED},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COL_NOTE_ID);
            int BodyIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COL_NOTE_BODY);
            int CreatedIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COL_NOTE_BODY);
//           // shoppingItems.add(new String[]{cursor.getString(price),cursor.getString(id)});
            NoteItems.add(
                    new Note(cursor.getInt(idIndex),
                            cursor.getString(BodyIndex),
                            cursor.getString(CreatedIndex)
                    )
            );
        }

        if (mAdapter == null) {
            mAdapter =  new HeadlinesAdapter(this, NoteItems);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(NoteItems);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public void addNote(View view) {
        Intent createNote = new Intent(this,CreateNote.class);
        this.startActivity(createNote);
    }

    public void deleteNote(@NonNull View view) {
        View parent = (View) view.getParent().getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.note_id);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(NoteContract.NoteEntry.TABLE,
                NoteContract.NoteEntry.COL_NOTE_ID + " = ?",
                new String[]{task});
        db.close();
        updateUI();
    }

}

