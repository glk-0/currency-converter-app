package com.example.currencyconverterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CONVERSION_TABLE = "CONVERSION_TABLE";
    public static final String COLUMN_BASE_CURRENCY = "BASE_CURRENCY";
    public static final String COLUMN_TARGET_CURRENCY = "TARGET_CURRENCY";
    public static final String COLUMN_BASE_AMOUNT = "BASE_AMOUNT";
    public static final String COLUMN_TARGET_AMOUNT = "TARGET_AMOUNT";
    public static final String COLUMN_CHANGE_RATE = "CHANGE_RATE";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + CONVERSION_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BASE_CURRENCY + " TEXT, " + COLUMN_TARGET_CURRENCY + " TEXT, " + COLUMN_BASE_AMOUNT + " REAL, " + COLUMN_TARGET_AMOUNT + " REAL, " + COLUMN_CHANGE_RATE + " REAL) ";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Conversion conversion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BASE_CURRENCY, conversion.getBaseCurrencyCode());
        cv.put(COLUMN_TARGET_CURRENCY, conversion.getTargetCurrencyCode());
        cv.put(COLUMN_BASE_AMOUNT, conversion.getBaseAmount());
        cv.put(COLUMN_TARGET_AMOUNT,conversion.getTargetAmount());
        cv.put(COLUMN_CHANGE_RATE, conversion.getChangeRate());

        long insert = db.insert(CONVERSION_TABLE,null,cv);
        boolean success = insert ==-1 ?  false : true;

        return success;
    }

    public List<Conversion> getAllConversions(){
        List<Conversion> conversions = new ArrayList<>();

        String queryString = "SELECT * FROM "+ CONVERSION_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                String baseCurrency = cursor.getString(1);
                String targetCurrency = cursor.getString(2);
                Double baseAmount =cursor.getDouble(3);
                Double targetAmount =cursor.getDouble(4);
                Double changeRate =cursor.getDouble(5);

                Conversion conversion  = new Conversion(baseCurrency,targetCurrency, baseAmount, targetAmount, changeRate);
                conversions.add(conversion);


            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return conversions;
    }
    public boolean clearAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + CONVERSION_TABLE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }
}
