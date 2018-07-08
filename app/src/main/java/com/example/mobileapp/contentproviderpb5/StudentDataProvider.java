package com.example.mobileapp.contentproviderpb5;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentDataProvider extends ContentProvider {
    private StudentDatabaseHelper helper;
    private SQLiteDatabase db;

    public static final String AUTHORITY = "com.example.mobileapp.contentproviderpb5";
    public static final String CONTENT_STRING = "content://"+AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_STRING);

    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,StudentDatabaseHelper.TABLE_STUDENT,1);
        sUriMatcher.addURI(AUTHORITY,StudentDatabaseHelper.TABLE_STUDENT+"/#",2);
    }

    public StudentDataProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long insertedRow = db.insert(StudentDatabaseHelper.TABLE_STUDENT,null,values);
        Uri insertedUri = Uri.parse(CONTENT_STRING+"/"+StudentDatabaseHelper.TABLE_STUDENT+"/"+insertedRow);
        return insertedUri;
    }

    @Override
    public boolean onCreate() {
        helper = new StudentDatabaseHelper(getContext());
        db = helper.getWritableDatabase();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)){
            case 1:
                return db.query(StudentDatabaseHelper.TABLE_STUDENT,projection,selection,null,null,null,null);
            case 2:
                String id = uri.getLastPathSegment();
                return db.query(StudentDatabaseHelper.TABLE_STUDENT,projection,StudentDatabaseHelper.COL_ID+"="+id,null,null,null,null);
                default:
                    throw new IllegalArgumentException("invalid path");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
