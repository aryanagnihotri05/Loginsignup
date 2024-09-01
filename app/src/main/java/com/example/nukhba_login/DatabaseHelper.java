package com.example.nukhba_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT)";
            db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public Boolean addContact(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASSWORD,password);

        long result = db.insert(TABLE_NAME,null,values);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checkmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where email = ?", new String[]{email});

        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where email = ? and password = ?", new String[]{email,password});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
