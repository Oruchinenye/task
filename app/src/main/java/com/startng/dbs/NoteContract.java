package com.startng.dbs;

import android.provider.BaseColumns;

public class NoteContract {
    public static final String DB_NAME = "com.example.shoppinglist.db";
    public static final int DB_VERSION = 1;

    public class NoteEntry implements BaseColumns {
        public static final String TABLE = "shopping";

        public static final String COL_NOTE_ID = "_id";
        public static final String COL_NOTE_BODY = "body";
        public static final String COL_NOTE_CREATED = "created_at";
    }

}