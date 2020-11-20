package com.example.empmgtsystem.EventList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DBManagerEvent extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "event.db";

    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "id";

    private static final String COLUMN_EVENTNAME = "eventname";

    private static final String COLUMN_DATE = "date";

    private static final String COLUMN_TIME = "time";

    private static final String COLUMN_LOCATION = "location";

    private static final String TABLE_NAME = "event_table";

    private static final String CREATE_TABLE = "Create Table " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EVENTNAME + " TEXT NOT NULL,"
            + COLUMN_DATE + " TEXT NOT NULL,"
            + COLUMN_TIME + " TEXT NOT NULL,"
            + COLUMN_LOCATION + " TEXT NOT NULL)";

    public DBManagerEvent(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public long addEventRecord(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EVENTNAME,event.getEventName());
        values.put(COLUMN_DATE,event.getDate());
        values.put(COLUMN_TIME,event.getTime());
        values.put(COLUMN_LOCATION,event.getLocation());

        return db.insert(TABLE_NAME,null, values);

    }

    public List<Event> getAllEventRecord() {

        List<Event> eventList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * From "+TABLE_NAME,null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String eventname = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                String location = cursor.getString(4);

                Event event = new Event(id,eventname,date,time,location);

                eventList.add(event);

            } while (cursor.moveToNext());

        }
        return eventList;
    }

    public int updateEventInfo(Event event) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EVENTNAME,event.getEventName());
        values.put(COLUMN_DATE,event.getDate());
        values.put(COLUMN_TIME,event.getTime());
        values.put(COLUMN_LOCATION,event.getLocation());

        return database.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(event.getId())});
    }

    public int deleteEventRecord(int e_id) {

        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(e_id)});
    }
}
