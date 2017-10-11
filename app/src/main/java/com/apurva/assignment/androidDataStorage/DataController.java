package com.apurva.assignment.androidDataStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController {
    private static final String DATABASE_NAME = "DataStorageAssignment.mDb";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ProductTable";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_REVIEW = "review";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_PRICE + " FLOAT NOT NULL, "
            + COLUMN_REVIEW + " TEXT);";
    private static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public DataController(Context context) {
        mDbHelper = new DataBaseHelper(context);
    }

    public void open() {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public long insert(String name, String description, Float price, String review) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_NAME, name);
        content.put(COLUMN_DESCRIPTION, description);
        content.put(COLUMN_PRICE, price);
        content.put(COLUMN_REVIEW, review);
        return mDb.insertOrThrow(TABLE_NAME, null, content);
    }

    public String retrieve(String searchText) {
        String searchResult = "Search Results:";
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '%" + searchText + "%';";
        Cursor cursor = mDb.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                searchResult += "\nItem Name: " + cursor.getString(0);
                searchResult += "\nItem Description: " + cursor.getString(1);
                searchResult += "\nPrice: " + cursor.getFloat(2);
                searchResult += "\nReview: " + cursor.getString(3) + "\n";
            } while (cursor.moveToNext());
        }
        cursor.close();

        return searchResult;
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(TABLE_CREATE);
            } catch(SQLiteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(TABLE_DROP);
            onCreate(db);
        }
    }
}

