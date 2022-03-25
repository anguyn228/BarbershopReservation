package com.example.barberreservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // User table
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERID = "USER" + COLUMN_ID;
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_TITLE = "TITLE";

    // Staff table
    public static final String STAFF_TABLE = "STAFF_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_uID = "staffId";
    public static final String COL_NAME = "name";
    public static final String COL_TITLE = "title";
    public static final String COL_BIO = "biography";
    public static final String COL_SHIFT = "SHIFT";

    //Customer Table
    public static final String CUSTOMER_TABLE = "customer";
    public static final String COL_USERID = "username";
    public static final String COL_CUSTOMERNAME = "name";
    public static final String COL_ADDRESS = "address";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "barbershopData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE WITH USER
        String userTable = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +COLUMN_TITLE + "TEXT,"+ COLUMN_USERID + " TEXT, " + COLUMN_PASSWORD + " TEXT )";
        db.execSQL(userTable);




        String staffTable = "CREATE TABLE " + STAFF_TABLE + "(" + COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_uID + "TEXT, " + COL_TITLE + " TEXT, " + COL_NAME + " TEXT, "  + " TEXT, " + COL_BIO + " TEXT, " + COL_SHIFT + " TEXT )" ;
        db.execSQL(staffTable);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP table if exists " + USER_TABLE);
        db.execSQL("DROP TABLE if exists " + STAFF_TABLE);
        onCreate(db);
    }

    public boolean addCustomerInfo(CustomerModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USERID, model.getUsername());
        cv.put(COL_CUSTOMERNAME, model.getName());
        cv.put(COL_ADDRESS, model.getAddress());


        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean addUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, userModel.getName());
        cv.put(COLUMN_USERID, userModel.getUserId());
        cv.put(COLUMN_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteOne(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUMN_ID + " = " + userModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<StaffModel> retrieveAllStaffs() {
        ArrayList<StaffModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + STAFF_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and put them into the return list
            do {
                String name = cursor.getString(0);
                String staffId = cursor.getString(1);
                String Bio = cursor.getString(2);
                String Title = cursor.getString(3);
                String Shift = cursor.getString(4);
                StaffModel newUser = new StaffModel(name, staffId, Bio, Shift, Title);
                returnList.add(newUser);
            }
            while (cursor.moveToNext());
        } else {
            // error -> don't add anything to the list
        }
        // close the cursor and db after using them
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<UserModel> retrieveAllUsers() {
        ArrayList<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and put them into the return list
            do {
                int ID = cursor.getInt(0);
                String Title = cursor.getString(1);
                String Name = cursor.getString(2);
                String UserID = cursor.getString(3);
                String Password = cursor.getString(4);

                UserModel newUser = new UserModel( ID, Title, Name, UserID, Password);
                returnList.add(newUser);
            }
            while (cursor.moveToNext());
        } else {
            // error -> don't add anything to the list
        }
        // close the cursor and db after using them
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<StaffModel> allStaffs() {
        ArrayList<StaffModel> staffModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + STAFF_TABLE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        while (c.moveToNext()) {
            String staffid = c.getString(1);
            String name = c.getString(2);
            String bio = c.getString(4);
            String shift = c.getString(5);
            String title = c.getString(6);
            StaffModel model = new StaffModel(staffid, name, bio, shift, title);
            staffModels.add(model);
        }
        return staffModels;
    }
}
