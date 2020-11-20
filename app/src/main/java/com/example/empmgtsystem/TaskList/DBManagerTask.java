package com.example.empmgtsystem.TaskList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.empmgtsystem.FriendList.Friend;

import java.util.ArrayList;
import java.util.List;

public class DBManagerTask extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";

    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "id";

    private static final String COLUMN_TASK = "task";

    private static final String COLUMN_LOCATION = "location";

    private static final String COLUMN_STATUS = "status";

    private static final String TABLE_NAME = "task_table";

    private static final String CREATE_TABLE = "Create Table " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TASK + " TEXT NOT NULL,"
            + COLUMN_LOCATION + " TEXT NOT NULL,"
            + COLUMN_STATUS + " TEXT NOT NULL)";

    public DBManagerTask(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public long addTaskRecord(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK,task.getTaskName());
        values.put(COLUMN_LOCATION,task.getLocation());
        values.put(COLUMN_STATUS,task.getStatus());

        return db.insert(TABLE_NAME,null, values);

    }

    public List<Task> getAllTaskCompletedRecord() {

        List<Task> taskList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * From "+TABLE_NAME+" Where status = 'completed'",null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskname = cursor.getString(1);
                String loation = cursor.getString(2);
                String status = cursor.getString(3);

                Task task = new Task(id,taskname,loation,status);

                taskList.add(task);

            } while (cursor.moveToNext());

        }
        return taskList;
    }

    public List<Task> getAllTaskNotCompletedRecord() {

        List<Task> taskList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * From "+TABLE_NAME+" Where status = 'not completed'",null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskname = cursor.getString(1);
                String loation = cursor.getString(2);
                String status = cursor.getString(3);

                Task task = new Task(id,taskname,loation,status);

                taskList.add(task);

            } while (cursor.moveToNext());

        }
        return taskList;
    }

    public int updateTaskInfo(Task task) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK,task.getTaskName());
        values.put(COLUMN_LOCATION,task.getLocation());
        values.put(COLUMN_STATUS,task.getStatus());


        return database.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(task.getId())});
    }



    public int deleteTaskRecord(int e_id) {

        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(e_id)});
    }
}
