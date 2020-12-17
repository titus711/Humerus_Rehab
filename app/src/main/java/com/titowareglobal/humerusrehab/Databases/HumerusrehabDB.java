package com.titowareglobal.humerusrehab.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HumerusrehabDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "Humerusrehab.db";
    private static final int DB_VER = 1;

    public HumerusrehabDB(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public int getSettingsMode(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlselect = {"Mode"};
        String sqlTable = "Settings";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlselect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));
    }

    public void saveSettingsMode(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Settings SET Mode = " + value;
        db.execSQL(query);
    }


}
