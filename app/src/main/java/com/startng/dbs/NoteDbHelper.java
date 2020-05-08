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
        String createTable = "CREATE TABLE " + NoteContract.ShoppingEntry.TABLE + " ( " +
                NoteContract.ShoppingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NoteContract.ShoppingEntry.COL_NOTE_BODY + " DECIMAL(12,2) NOT NULL, " +
                NoteContract.ShoppingEntry.COL_NOTE_CREATED + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteContract.ShoppingEntry.TABLE);
        onCreate(db);
    }
}