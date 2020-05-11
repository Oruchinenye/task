package com.startng.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.startng.dbs.NoteContract;
import com.startng.dbs.NoteDbHelper;

public class EditNote extends AppCompatActivity {
    private static final String TAG = "EditNote";
    private NoteDbHelper mHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note2);
        String headline = getIntent().getStringExtra("text");
        EditText textView = findViewById(R.id.editText);
        id = getIntent().getStringExtra("_id");
        textView.setText(headline);
        mHelper = new NoteDbHelper(this);
    }

    public void update(View view) {
        EditText task = findViewById(R.id.editText);
        String item = String.valueOf(task.getText());

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COL_NOTE_BODY, item);
        db.update(NoteContract.NoteEntry.TABLE,
                values,NoteContract.NoteEntry.COL_NOTE_ID + " = ?",
                new String[]{id});
        db.close();
        this.finish();
    }
}
