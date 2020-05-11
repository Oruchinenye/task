package com.startng.newsapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.startng.dbs.NoteContract;
import com.startng.dbs.NoteDbHelper;

public class CreateNote extends AppCompatActivity {
    private static final String TAG = "CreateNote";
    private NoteDbHelper mHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        mHelper = new NoteDbHelper(this);

    }

    public void save(View view) {
        EditText task = findViewById(R.id.editText);
        String item = String.valueOf(task.getText());

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COL_NOTE_BODY, item);
        db.insertWithOnConflict(NoteContract.NoteEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        this.finish();
    }
}
