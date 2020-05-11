package com.startng.dbs;

import java.util.GregorianCalendar;
import java.sql.Date;
public class Note {
    public int note__id;
    public String note_body;
    public String note_created_at;
    public Note(int id, String body, String date ) {
        note__id = id;
        note_body = body;
        note_created_at = date;
    }
}
