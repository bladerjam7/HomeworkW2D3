package com.americanairlines.homeworkw2d3.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.americanairlines.homeworkw2d3.model.data.Equipment;

public class EquipmentDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "equipment.db";
    public static int DATABASE_VERSION = 1;

    public static final String EQUIPMENT_TABLE_NAME = "EQUIPMENT";
    public static final String COLUMN_EQUIPMENT_ID = "equipment_id";
    public static final String COLUMN_NAME = "equipment_name";
    public static final String COLUMN_PRICE = "equipment_price";
    public static final String COLUMN_DESCRIPTION = "equipment_description";
    public static final String COLUMN_IMGURL = "equipment_img_url";

    public EquipmentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + EQUIPMENT_TABLE_NAME + " ("
                + COLUMN_EQUIPMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PRICE + " TEXT, "
                + COLUMN_DESCRIPTION+ " TEXT, "
                + COLUMN_IMGURL + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + EQUIPMENT_TABLE_NAME;
        db.execSQL(update);
        DATABASE_VERSION = newVersion;
        onCreate(db);

    }

    public void insertEquipment(Equipment equipment){
        ContentValues equipmentContent = new ContentValues();
        equipmentContent.put(COLUMN_NAME, equipment.getName());
        equipmentContent.put(COLUMN_PRICE, equipment.getPrice());
        equipmentContent.put(COLUMN_DESCRIPTION, equipment.getDescription());
        equipmentContent.put(COLUMN_IMGURL, equipment.getImgUrl());

        SQLiteDatabase database = getWritableDatabase();
        database.insert(EQUIPMENT_TABLE_NAME, null, equipmentContent);
    }

    public Cursor getAllEquipments(){
        Cursor allEquipments = getReadableDatabase().rawQuery("SELECT * FROM " + EQUIPMENT_TABLE_NAME, null, null);

        return allEquipments;
    }
}
