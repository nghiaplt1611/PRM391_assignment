package com.example.gtw_101.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtw_101.model.Guest;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GuessTheWord";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Guest_Management";

    private static final String KEY_LEVEL = "level";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ANSWER_OF_QUESTION = "answer_of_question";
    private static final String KEY_NUM_OF_LETTER_SHOWN = "num_of_letter_shown";
    private static final String KEY_ACHIEVEMENT = "achievement";



    /**
     * Create constructor with 1 parameter
     *
     * @param context storing the context of the application where the database is created
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Override method onCreate for creating table (after database has been created)
     *
     * @param db storing the SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createGuestTable = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s INTEGER, %s TEXT)",
                TABLE_NAME, KEY_LEVEL, KEY_SCORE, KEY_ANSWER_OF_QUESTION, KEY_NUM_OF_LETTER_SHOWN, KEY_ACHIEVEMENT);
        db.execSQL(createGuestTable);
    }

    /**
     * Override method onUpgrade for upgrading the database when needed (higher database version)
     *
     * @param db         storing SQLite database
     * @param oldVersion storing the old version
     * @param newVersion storing the new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropLecturerTable = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropLecturerTable);
        onCreate(db);
    }


    /**
     * Create method addGuestInfo() to add the guest's information into the SQLite database
     *
     * @param guest storing guest's information
     */
    public void addGuestInfo(Guest guest) {

        // Notify the database to be read and written
        SQLiteDatabase db = this.getWritableDatabase();

        // Create object from class ContentValues to store a set the values for the added object
        ContentValues values = new ContentValues();

        // Add data to the value by providing the attribute and its value
        values.put(KEY_LEVEL, guest.getLevel());
        values.put(KEY_SCORE, guest.getScore());
        values.put(KEY_ANSWER_OF_QUESTION, guest.getAnswerOfQuestion());
        values.put(KEY_NUM_OF_LETTER_SHOWN, guest.getNumOfLetterShown());
        values.put(KEY_ACHIEVEMENT, guest.getAchievements());

        // Call the insert method from the database
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void updateGuestInfo(Guest updatedGuest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LEVEL, updatedGuest.getLevel());
        values.put(KEY_SCORE, updatedGuest.getScore());
        values.put(KEY_ANSWER_OF_QUESTION, updatedGuest.getAnswerOfQuestion());
        values.put(KEY_NUM_OF_LETTER_SHOWN, updatedGuest.getNumOfLetterShown());
        values.put(KEY_ACHIEVEMENT, updatedGuest.getAchievements());

        db.update(TABLE_NAME, values, null, null);
        db.close();
    }

    public Guest getGuestInfo(){
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Guest guest = new Guest(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4));
        return guest;
    }

}
