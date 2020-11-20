package com.example.empmgtsystem.FriendList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBManagerFriend extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Friend.db";

    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "id";

    private static final String COLUMN_FNAME = "fname";

    private static final String COLUMN_LNAME = "lname";

    private static final String COLUMN_GENTLE = "gentle";

    private static final String COLUMN_AGE = "age";

    private static final String COLUMN_ADDRESS = "address";

    private static final String COLUMN_IMAGE = "image";

    private static final String TABLE_NAME = "friend_table";

    private static final String CREATE_TABLE = "Create Table " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FNAME + " TEXT NOT NULL,"
            + COLUMN_LNAME + " TEXT NOT NULL,"
            + COLUMN_GENTLE + " TEXT NOT NULL,"
            + COLUMN_AGE + " TEXT NOT NULL,"
            + COLUMN_ADDRESS + " TEXT NOT NULL,"
            + COLUMN_IMAGE + " TEXT NOT NULL)";


    public DBManagerFriend(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
       onCreate(db);
    }

    public long addFriendRecord(Friend friend) {
       SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FNAME,friend.getFname());
        values.put(COLUMN_LNAME,friend.getLname());
        values.put(COLUMN_GENTLE,friend.getGentle());
        values.put(COLUMN_AGE,friend.getAge());
        values.put(COLUMN_ADDRESS,friend.getAddress());
        values.put(COLUMN_IMAGE,friend.getImage());
        return db.insert(TABLE_NAME,null, values);

    }

    public List<Friend> getAllFriendRecord() {

        List<Friend> friendList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * From "+TABLE_NAME,null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String gentle = cursor.getString(3);
                String age = cursor.getString(4);
                String address = cursor.getString(5);
                String image = cursor.getString(6);
                Friend friend = new Friend(id,fname,lname,gentle,age,address,image);

                friendList.add(friend);

            } while (cursor.moveToNext());

        }
        return friendList;
    }

    public int updateFriendInfo(Friend friend) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FNAME,friend.getFname());
        values.put(COLUMN_LNAME,friend.getLname());
        values.put(COLUMN_GENTLE,friend.getGentle());
        values.put(COLUMN_AGE,friend.getAge());
        values.put(COLUMN_ADDRESS,friend.getAddress());
        values.put(COLUMN_IMAGE,friend.getImage());
        return database.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(friend.getId())});
    }

    public int deleteFriendRecord(int e_id) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(e_id)});
    }
}
