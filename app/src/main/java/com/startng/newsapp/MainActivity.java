package com.startng.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.startng.dbs.Note;
import com.startng.dbs.NoteContract;
import com.startng.dbs.NoteDbHelper;
import com.startng.dbs.NoteList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find textviews, get the extras and assign both to each other
        TextView textView = findViewById(R.id.textView4);
        String headline = getIntent().getStringExtra("note");
        textView.setText(headline);
    }

}
