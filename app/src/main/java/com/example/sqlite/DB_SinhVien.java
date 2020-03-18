package com.example.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DB_SinhVien extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "SINHVIEN";
    private static final String TEN = "TEN";
    private static final String NGAYSINH = "NGAYSINH";
    private static final String TRUONG = "TRUONG";
    private static final String GIOITINH = "GIOITINH";
    private static final String SOTHICH = "SOTHICH";

    public DB_SinhVien(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddSV(String hoten, String ngaysinh, String truonghoc, int gioitinh, String sothich){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,hoten);
        values.put(NGAYSINH, ngaysinh);
        values.put(TRUONG, truonghoc);
        values.put(GIOITINH, gioitinh);
        values.put(SOTHICH, sothich);

        database.insert(TABLE_NAME, null, values);
        database.close();
    }
}
