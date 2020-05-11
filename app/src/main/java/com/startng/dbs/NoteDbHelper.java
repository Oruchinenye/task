package com.startng.dbs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NoteDbHelper extends SQLiteOpenHelper {
    public NoteDbHelper(Context context) {
        super(context, NoteContract.DB_NAME, null, NoteContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + NoteContract.NoteEntry.TABLE + " ( " +
                NoteContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NoteContract.NoteEntry.COL_NOTE_BODY + " DECIMAL(12,2) NOT NULL, " +
                NoteContract.NoteEntry.COL_NOTE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE);
        onCreate(db);
    }
}