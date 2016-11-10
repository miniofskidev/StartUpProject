package com.codefobi.startupproject.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.models.ReadContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GOD";

    private static final String CONTENT_TABLE = "content";
    private static final String READ_TABLE = "read";

    private static final String WHODOYOU = "whodoyou";

    /*Content Table Column names*/
    private static final String CONTENT_ID = "id";
    private static final String CONTENT_SPECIFIER = "Specifier";
    private static final String CONTENT_TITLE = "title";
    private static final String CONTENT_BODY = "body";

    /*Read Table Column names*/
    private static final String READ_ID = "id";
    private static final String READ_SPECIFIER = "Specifier";
    private static final String READ_TITLE = "title";
    private static final String READ_BODY = "body";
    private static final String READ_IMAGE_PATH = "imagePath";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE IF NOT EXISTS " + CONTENT_TABLE + "(" +
                CONTENT_ID + " INTEGER PRIMARY KEY" + "," + WHODOYOU + " TEXT" + "," + CONTENT_SPECIFIER + " INTEGER" + "," +
                CONTENT_TITLE + " TEXT" + "," + CONTENT_BODY + " TEXT" + ")";

        String CREATE_READ_TABLE = "CREATE TABLE IF NOT EXISTS " + READ_TABLE + "(" +
                READ_ID + " INTEGER PRIMARY KEY" + "," + WHODOYOU + " TEXT" + "," + READ_SPECIFIER + " TEXT" + "," +
                READ_TITLE + " TEXT" + "," + READ_BODY + " TEXT" + "," + READ_IMAGE_PATH + " TEXT" + ")";

        db.execSQL(CREATE_CONTENT_TABLE);
        db.execSQL(CREATE_READ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + READ_TABLE);
        onCreate(db);
    }

    public void addReadContent(ReadContent readContent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WHODOYOU , readContent.getWhodoyou());
        values.put(READ_SPECIFIER, readContent.getSpecifier());
        values.put(READ_TITLE, readContent.getTitle());
        values.put(READ_BODY, readContent.getBody());
        values.put(READ_IMAGE_PATH, readContent.getImagePath());

        db.insert(READ_TABLE, null, values);
        db.close();

    }

    public void addContent(Content content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WHODOYOU , content.getWhodoyou());
        values.put(CONTENT_SPECIFIER, content.getSpecifier());
        values.put(CONTENT_TITLE, content.getTitle());
        values.put(CONTENT_BODY, content.getBody());

        db.insert(CONTENT_TABLE, null, values);
        db.close();
    }

    public Content getContent(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(CONTENT_TABLE, new String[]{CONTENT_ID, WHODOYOU , CONTENT_SPECIFIER, CONTENT_TITLE, CONTENT_BODY},
                CONTENT_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Content content = new Content(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        return content;
    }

    public List<Content> getAllContents() {

        List<Content> contents = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + CONTENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Content content = new Content();
                content.setID(Integer.parseInt(cursor.getString(0)));
                content.setWhodoyou(cursor.getString(1));
                content.setSpecifier(cursor.getString(2));
                content.setTitle(cursor.getString(3));
                content.setBody(cursor.getString(4));

                contents.add(content);

                Log.d("amin" , "Lets Do it :" + content.getWhodoyou());
            }
        }
        cursor.close();

        return contents;
    }

    public List<ReadContent> getReadBy(String WhoDoYou){
        List<ReadContent> contentList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + READ_TABLE + " WHERE " + WHODOYOU + " = " + "'" + WhoDoYou + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);

        Log.d("amin" , "Initializing the cursor");

        if (cursor != null){
            while (cursor.moveToNext()){
                ReadContent content = new ReadContent();
                content.setWhodoyou(cursor.getString(1));
                content.setSpecifier(cursor.getString(2));
                content.setTitle(cursor.getString(3));
                content.setBody(cursor.getString(4));
                content.setImagePath(cursor.getString(5));

                contentList.add(content);

                Log.d("amin" , "Lets do it :" + String.valueOf(content.getID()));
            }
        }
        cursor.close();

        Log.d("AMIN" , "Loading Items from DataBase with a filter");

        return contentList;

    }

    public List<ReadContent> getAllReads() {
        List<ReadContent> contentList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + READ_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                ReadContent content = new ReadContent();
                content.setID(Integer.parseInt(cursor.getString(0)));
                content.setWhodoyou(cursor.getString(1));
                content.setSpecifier(cursor.getString(2));
                content.setTitle(cursor.getString(3));
                content.setBody(cursor.getString(4));
                content.setImagePath(cursor.getString(5));

                contentList.add(content);
                Log.d("AMIN" , "lets do it :" + content.getWhodoyou());
            }
        }
        cursor.close();

        Log.d("AMIN", "loading Form DataBase");

        return contentList;

    }

}
