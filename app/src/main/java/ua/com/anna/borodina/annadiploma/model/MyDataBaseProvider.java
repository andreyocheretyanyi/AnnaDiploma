package ua.com.anna.borodina.annadiploma.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class MyDataBaseProvider extends SQLiteOpenHelper {

    private final static String ROOMS_TABLE_NAME = "rooms";
    private final static String[] ROOMS_COLUMN = {"id", "number", "water", "free", "price","block_id","date"};
    private final static String BLOCKS_TABLE_NAME = "blocks";
    private final static String[] BLOCKS_COLUMN = {"id", "name"};


    MyDataBaseProvider(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+BLOCKS_TABLE_NAME
                +"("+"_id"+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                BLOCKS_COLUMN[1]+" TEXT NOT NULL," +
                "UNIQUE("+BLOCKS_COLUMN[1]+"));");
        db.execSQL("create table "+ROOMS_TABLE_NAME+"(" +"_id"+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
        ROOMS_COLUMN[1]+" INTEGER NOT NULL,"+ROOMS_COLUMN[2]+" INTEGER NOT NULL," + ROOMS_COLUMN[3]+ " INTEGER NOT NULL,"+
        ROOMS_COLUMN[4]+" INTEGER NOT NULL, " +
                ROOMS_COLUMN[5]+" INTEGER NOT NULL, " +
                ROOMS_COLUMN[6]+" TEXT NOT NULL DEFAULT 'no',"+
                "FOREIGN KEY (block_id) REFERENCES blocks(id)" +
                ");");
    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
            if(newVersion > oldVersion){
                String upgradeQuery = "ALTER TABLE rooms ADD COLUMN date TEXT";
                    db.execSQL(upgradeQuery);
            }
        }
}
