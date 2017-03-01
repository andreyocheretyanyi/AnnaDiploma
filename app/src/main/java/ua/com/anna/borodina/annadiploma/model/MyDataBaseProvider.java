package ua.com.anna.borodina.annadiploma.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Андрей on 19.02.2017.
 */

class MyDataBaseProvider extends SQLiteOpenHelper {

    private final static String ROOMS_TABLE_NAME = "rooms";
    private final static String[] ROOMS_COLUMN = {"id", "water", "free", "price","block_id"};
    private final static String BLOCKS_TABLE_NAME = "blocks";
    private final static String[] BLOCKS_COLUMN = {"id", "name"};


    MyDataBaseProvider(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+BLOCKS_TABLE_NAME
                +"("+"_id"+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                BLOCKS_COLUMN[1]+" TEXT NOT NULL);");
        db.execSQL("create table "+ROOMS_TABLE_NAME+"(" +"_id"+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
        ROOMS_COLUMN[1]+" INTEGER NOT NULL,"+ROOMS_COLUMN[2]+" INTEGER NOT NULL," + ROOMS_COLUMN[3]+ " INTEGER NOT NULL,"+
        ROOMS_COLUMN[4]+" INTEGER NOT NULL, " +
                "FOREIGN KEY (block_id) REFERENCES blocks(id)" +
                ");");
    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){

        }
}
