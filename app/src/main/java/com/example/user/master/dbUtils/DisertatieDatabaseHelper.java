package com.example.user.master.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 14.05.2015.
 */
public class DisertatieDatabaseHelper {

    private SQLiteDatabase database = null;
    private static final String DATABASE_NAME = "disertatie.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_STATII = "statii";
    private static final String TABLE_NAME_LINII = "linii";
    private static final String TABLE_NAME_LEGATURI = "legaturi";
    private static final String TABLE_NAME_FORUM = "forum";
    private static final String TABLE_NAME_INREGISTRARI = "inregistrari";
    private static final String TIMETRACKER_COLUMN_ID = "_id";
    private static final String TIMETRACKER_COLUMN_STATII_NUME = "nume";
    private static final String TIMETRACKER_COLUMN_STATII_COD = "cod";
    private static final String TIMETRACKER_COLUMN_STATII_DESCRIERE= "descriere";
    private static final String TIMETRACKER_COLUMN_LINII_NUMAR = "numar";
    private static final String TIMETRACKER_COLUMN_LINII_INTERVALSUCCEDARE = "interval_succedare";
    private static final String TIMETRACKER_COLUMN_LINII_DESCRIERE= "descriere";
    private static final String TIMETRACKER_COLUMN_lEGATURI_IDSTATIE = "id_statie";
    private static final String TIMETRACKER_COLUMN_lEGATURI_IDLINIE = "id_linie";
    private static final String TIMETRACKER_COLUMN_F0RUM_TEXT = "text";
    private static final String TIMETRACKER_COLUMN_FORUM_DATA = "data";
    private static final String TIMETRACKER_COLUMN_INREGISTRARI_NUME = "nume";
    private static final String TIMETRACKER_COLUMN_INREGISTRARI_PRENUME = "prenume";
    private static final String TIMETRACKER_COLUMN_INREGISTRARI_USERNAME = "username";
    private static final String TIMETRACKER_COLUMN_INREGISTRARI_PAROLA = "parola";
    private static final String TIMETRACKER_COLUMN_INREGISTRARI_TIPUSER = "tipUser";
    private TimeTrackerOpenHelper timeTrackerOpenHelper = null;

    public DisertatieDatabaseHelper(Context context){

        timeTrackerOpenHelper = new TimeTrackerOpenHelper(context);
        database = timeTrackerOpenHelper.getWritableDatabase();
    }

    private class TimeTrackerOpenHelper extends SQLiteOpenHelper {

        public TimeTrackerOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_STATII + " ( " + TIMETRACKER_COLUMN_ID + " integer primary key, " + TIMETRACKER_COLUMN_STATII_NUME + " text, " + TIMETRACKER_COLUMN_STATII_COD + " integer, " + TIMETRACKER_COLUMN_STATII_DESCRIERE + " text)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_LINII + " ( " + TIMETRACKER_COLUMN_ID + " integer primary key, " + TIMETRACKER_COLUMN_LINII_NUMAR + " integer, " + TIMETRACKER_COLUMN_LINII_INTERVALSUCCEDARE + " integer, " + TIMETRACKER_COLUMN_LINII_DESCRIERE + " text)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_LEGATURI + " ( " + TIMETRACKER_COLUMN_ID + " integer primary key, " + TIMETRACKER_COLUMN_lEGATURI_IDLINIE + " integer, " + TIMETRACKER_COLUMN_lEGATURI_IDSTATIE + " integer)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_FORUM + " ( " + TIMETRACKER_COLUMN_ID + " integer primary key, " + TIMETRACKER_COLUMN_F0RUM_TEXT + " text, " + TIMETRACKER_COLUMN_FORUM_DATA + " text)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_INREGISTRARI + " ( " + TIMETRACKER_COLUMN_ID + " integer primary key, " + TIMETRACKER_COLUMN_INREGISTRARI_NUME + " text, " + TIMETRACKER_COLUMN_INREGISTRARI_PRENUME + " text, " + TIMETRACKER_COLUMN_INREGISTRARI_USERNAME + " text, " + TIMETRACKER_COLUMN_INREGISTRARI_PAROLA + " text, " + TIMETRACKER_COLUMN_INREGISTRARI_TIPUSER + " text)");
            sqLiteDatabase.execSQL(QueriesStrings.INSERT_DATA_INTO_LINII);
            sqLiteDatabase.execSQL(QueriesStrings.INSERT_DATA_INTO_STATII);
            sqLiteDatabase.execSQL(QueriesStrings.INSERT_DATA_INTO_LEGATURI);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_STATII);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_LINII);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_LEGATURI);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_FORUM);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_INREGISTRARI);
            this.onCreate(sqLiteDatabase);
        }
    }

    public void saveTimes(String nume, String cod, String descriere){
//        database.execSQL("INSERT INTO " + TABLE_NAME
//                + " (" + TIMETRACKER_COLUMN_TIME + ", " + TIMETRACKER_COLUMN_NOTES + ") VALUES ('" + time + "', '" + note + "')");

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIMETRACKER_COLUMN_STATII_NUME, nume);
        contentValues.put(TIMETRACKER_COLUMN_STATII_COD, cod);
        contentValues.put(TIMETRACKER_COLUMN_STATII_DESCRIERE, descriere);

        database.insert(TABLE_NAME_STATII, null, contentValues);
    }

    public Cursor getAllTimeRecords(){
        return database.rawQuery("SELECT * FROM " + TABLE_NAME_STATII, null);
    }

    public List<String> getAllLiniiNumarByTip(String tip){
        List<String> linii = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT " + TIMETRACKER_COLUMN_LINII_NUMAR + " FROM " + TABLE_NAME_LINII
                + " WHERE " + TIMETRACKER_COLUMN_LINII_DESCRIERE + " =?", new String[] {tip});

        while(cursor.moveToNext()){
            linii.add(String.valueOf(cursor.getInt(0)));
        }
        cursor.close();

        return linii;
    }

    public Cursor getStatiiByLinie(String numarLinie){
        return database.rawQuery("SELECT s."+ TIMETRACKER_COLUMN_STATII_NUME + ", s." +
                TIMETRACKER_COLUMN_STATII_DESCRIERE +" FROM " + TABLE_NAME_STATII + " s, "
                + TABLE_NAME_LEGATURI+ " leg, " + TABLE_NAME_LINII  + " lin WHERE leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDSTATIE + "=s." + TIMETRACKER_COLUMN_ID + " AND leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDLINIE + "=lin." + TIMETRACKER_COLUMN_ID +
                " AND lin." + TIMETRACKER_COLUMN_LINII_NUMAR + "=?", new String[] {numarLinie}); }

    public HashMap<String, HashMap<String, String>> getStatiiAndLinii(){
        HashMap<String, HashMap<String, String>> result = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT lin." + TIMETRACKER_COLUMN_LINII_NUMAR + ", s."+ TIMETRACKER_COLUMN_STATII_NUME + ", s." +
                TIMETRACKER_COLUMN_STATII_DESCRIERE +" FROM " + TABLE_NAME_STATII + " s, "
                + TABLE_NAME_LEGATURI+ " leg, " + TABLE_NAME_LINII  + " lin WHERE leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDSTATIE + "=s." + TIMETRACKER_COLUMN_ID + " AND leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDLINIE + "=lin." + TIMETRACKER_COLUMN_ID, null);
                while(cursor.moveToNext()) {
                    if(result.get(cursor.getString(0)) == null){
                        result.put(cursor.getString(0), new HashMap<String, String>());
                    }
                    result.get(cursor.getString(0)).put(cursor.getString(1), cursor.getString(2));
                }
        return result;
    }

    public List<String> getLiniiByStatie(String numeStatie){
        List<String> linii = new ArrayList<>();
        Cursor cursor =  database.rawQuery("SELECT l."+ TIMETRACKER_COLUMN_LINII_NUMAR
                 +" FROM " + TABLE_NAME_LINII + " l, "
                + TABLE_NAME_LEGATURI+ " leg, " + TABLE_NAME_STATII  + " s WHERE leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDLINIE + "=l." + TIMETRACKER_COLUMN_ID + " AND leg." +
                TIMETRACKER_COLUMN_lEGATURI_IDSTATIE + "=s." + TIMETRACKER_COLUMN_ID +
                " AND s." + TIMETRACKER_COLUMN_STATII_NUME + "=?", new String[] {numeStatie});

        while(cursor.moveToNext()){
            linii.add(cursor.getString(0));
        }
        return linii;
    }

    public List<String> getNumeStatiiList(){
        List<String> statii = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT " + TIMETRACKER_COLUMN_STATII_NUME + " FROM " + TABLE_NAME_STATII, null);

        while(cursor.moveToNext()){
            statii.add(cursor.getString(0));
        }
        return statii;
    }
}