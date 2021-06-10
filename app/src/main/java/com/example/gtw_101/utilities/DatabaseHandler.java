package com.example.gtw_101.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtw_101.model.Account;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GuessTheWord";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Account_Management";

    private static final String KEY_ID = "id";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";


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
        String createLecturerTable = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_FULL_NAME, KEY_USERNAME, KEY_PASSWORD);
        db.execSQL(createLecturerTable);
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
     * Create method addNewAccount() to add new account
     *
     * @param account storing account
     */
    public void addNewAccount(Account account) {

        // Notify the database to be read and written
        SQLiteDatabase db = this.getWritableDatabase();

        // Create object from class ContentValues to store a set the values for the added object
        ContentValues values = new ContentValues();

        // Add data to the value by providing the attribute and its value
        values.put(KEY_FULL_NAME, account.getFullName());
        values.put(KEY_USERNAME, account.getUsername());
        values.put(KEY_PASSWORD, MD5Hashing.getMD5Hash(account.getPassword()));

        // Call the insert method from the database
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    /**
     * Create method checkExistedAccount() to check for existed account
     *
     * @param username storing the username needed to be checked
     * @return true if account existed, else false
     */
    public boolean checkExistedAccount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_USERNAME + " = ?", new String[]{String.valueOf(username)}, null, null, null);
        return cursor.moveToFirst() && cursor.getCount() > 0;
    }

    /**
     * Create method login() to login to login into the game
     *
     * @param username storing username
     * @param password storing password
     * @return the account if login success, else return null
     */
    public Account login(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_USERNAME + " = ? AND " + KEY_PASSWORD + " = ?", new String[]{username, MD5Hashing.getMD5Hash(password)}, null, null, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0){
            Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return account;
        }
        return null;

    }

}
