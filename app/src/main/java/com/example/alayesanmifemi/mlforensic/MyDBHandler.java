package com.example.alayesanmifemi.mlforensic;

/**
 * Created by Alayesanmi Femi on 07/07/2018.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "forensics.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CASES = "cases";
    private static final String COLUMN_ID_USERS = "id";
    private static final String COLUMN_FIRSTNAME_USERS = "firstname";
    private static final String COLUMN_LASTNAME_USERS = "lastname";
    private static final String COLUMN_EMAIL_USERS = "email";
    private static final String COLUMN_PASSWORD_USERS = "password";

    private static final String COLUMN_ID_CASES = "case_id";
    private static final String COLUMN_TITLE_CASES = "title";
    private static final String COLUMN_LOCATION_CASES = "location";
    private static final String COLUMN_INCIDENT_DATE = "incident_date";
    private static final String COLUMN_DETECTIVE_ID_CASES = "detective_id";
    private static final String COLUMN_CRIME_TYPE_CASES = "crime_type";
    private static final String COLUMN_DESCRIPTION_CASES = "description";
    private static final String COLUMN_ARRIVAL_CASES = "arrivalTimeDate";
    private static final String COLUMN_DEPARTURE_CASES = "departureTimeDate";
    private static final String COLUMN_WEATHER_CASES = "weather";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT );",
                TABLE_USERS, COLUMN_ID_USERS, COLUMN_FIRSTNAME_USERS, COLUMN_LASTNAME_USERS, COLUMN_EMAIL_USERS, COLUMN_PASSWORD_USERS);
        db.execSQL(query);

        String query2 = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT " +
                "%s TEXT, %s TEXT, %s TEXT, %s TEXT );", TABLE_CASES, COLUMN_ID_CASES, COLUMN_TITLE_CASES, COLUMN_LOCATION_CASES, COLUMN_DETECTIVE_ID_CASES, COLUMN_CRIME_TYPE_CASES, COLUMN_DESCRIPTION_CASES, COLUMN_ARRIVAL_CASES, COLUMN_DEPARTURE_CASES, COLUMN_WEATHER_CASES, COLUMN_INCIDENT_DATE);

        db.execSQL(query2);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CASES);
        onCreate(db);
    }
    public void createUser(Users user){
        ContentValues values = new ContentValues();
        values.put( COLUMN_FIRSTNAME_USERS, user.getFirstname());
        values.put( COLUMN_LASTNAME_USERS, user.getLastname());
        values.put( COLUMN_EMAIL_USERS, user.getEmail());
        values.put( COLUMN_PASSWORD_USERS, user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void createCase(Cases cases){
        ContentValues values = new ContentValues();
        values.put( COLUMN_TITLE_CASES, cases.getTitle());
        values.put( COLUMN_ARRIVAL_CASES, cases.getArrivalTimeDate());
        values.put( COLUMN_DEPARTURE_CASES, cases.getDepartureTimeDate());
        values.put( COLUMN_CRIME_TYPE_CASES, cases.getCrimeType());
        values.put(COLUMN_INCIDENT_DATE, cases.getIncident_date());
        values.put( COLUMN_DESCRIPTION_CASES, cases.getDescription());
        values.put( COLUMN_DETECTIVE_ID_CASES, cases.getDetective_id());
        values.put( COLUMN_WEATHER_CASES, cases.getWeather());
        values.put( COLUMN_LOCATION_CASES, cases.getLocation());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CASES, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_ID_USERS
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAIL_USERS + " = ?";
        String[] selectionsArgs = { email };

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionsArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_ID_USERS
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAIL_USERS + " = ?" + " AND " + COLUMN_PASSWORD_USERS + " = ?";
        String[] selectionsArgs = { email, password };

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionsArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public String[] userProfile(String email) {
        String[] columns = {
                COLUMN_ID_USERS, COLUMN_FIRSTNAME_USERS, COLUMN_LASTNAME_USERS,
                COLUMN_EMAIL_USERS
        };
        String[] selectionsArgs = { email };
        SQLiteDatabase db = getWritableDatabase();
        String query = COLUMN_EMAIL_USERS + " = ?" ;
        Cursor c = db.query(TABLE_USERS, columns, query, selectionsArgs, null,null,null);
        c.moveToFirst();
        String[] dbString = new String[4];
        if(c.getCount() != 0) {
            int column_id = c.getColumnIndex("id");
           int column_firstname = c.getColumnIndex("firstname");
           int column_lastname = c.getColumnIndex("lastname");
           int column_email = c.getColumnIndex("email");
            dbString[0] = c.getString(column_firstname);
            dbString[1] = c.getString(column_lastname);
            dbString[2] = c.getString(column_email);
            dbString[3] = c.getString(column_id);
        }
        c.close();
        db.close();
        return dbString;
    }
    public void deleteUser(String username){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_FIRSTNAME_USERS
         + "=\"" + username +  "\";"  );
    }
    public void deleteCase(String casetitle){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CASES + " WHERE " + COLUMN_TITLE_CASES
                + "=\"" + casetitle +  "\";"  );
    }

    // Display Database content in the user table
    public String userDatabase(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("firstname")) != null){
                dbString +=(c.getString(c.getColumnIndex("firstname")));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }
    public void userLogin(String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + "WHERE email = " + email + " AND " +
                " password = " + password;
        db.execSQL(query);
    }

    public String casesDatabase(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CASES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("title")) != null){
                dbString +=(c.getString(c.getColumnIndex("title")));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }


 }

