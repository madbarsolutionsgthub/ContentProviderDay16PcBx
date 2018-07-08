package com.example.mobileapp.contentproviderpb5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_STUDENT = "tbl_student";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_DEPARTMENT = "department";
    public static final String CREATE_TABLE_STUDENT = "create table "+TABLE_STUDENT+"("
            +COL_ID+" integer primary key, "
            +COL_NAME+" text, "
            +COL_DEPARTMENT+" text);";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
