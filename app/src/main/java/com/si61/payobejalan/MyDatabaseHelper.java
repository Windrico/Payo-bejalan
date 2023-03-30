package com.si61.payobejalan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_payo_bejalan";
    private static  final int DATABASE_VERSION = 1;
    private static final String TABLE_NAMA ="tbl_destinasi";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_ALAMAT ="alamat";
    private static final String FIELD_JAM = "jam";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        CREATE TABLE tbl_destinasi (
        FIELD_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        NAMA varchar(50),
        text INTEGER,
         jam varchar(30) ;
        */
        String query = "Create Table "+TABLE_NAMA+"("+
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FIELD_NAMA + " VARCHAR(50), " +
                FIELD_ALAMAT +" TEXT," +
                FIELD_JAM + " VARCHAR(30) " +
                ");";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAMA;
        db.execSQL(query);
        onCreate(db);

    }
    public long tambahData(String nama, String alamat, String jam){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_ALAMAT , alamat);
        cv.put(FIELD_JAM, jam);

        long eksekusi = db.insert(TABLE_NAMA, null, cv);
        return eksekusi;
    }
    public Cursor bacaDataDestinasi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAMA;

        Cursor varCursor = null;
        if(db!=null){
            varCursor = db.rawQuery(query, null);
        }

        return varCursor;
    }
    public long ubahData(String id,String nama, String alamat, String jam){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_ALAMAT , alamat);
        cv.put(FIELD_JAM, jam);

        long eksekusi = db.update(TABLE_NAMA,cv,"id = ?",new String[]{id});
        return eksekusi;
    }
    public long hapusData(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        long eksekusi = db.delete(TABLE_NAMA,"id = ?",new String[]{id});
        return eksekusi;
    }

}











