package ua.com.anna.borodina.annadiploma.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;



public class DatabaseProviderImpl {
    private Context cntx;
    private MyDataBaseProvider dh;
    public static final String WATER_COLUMN = "water";
    public static final String NUMBER_COLUMN = "number";
    public static final String FREE_COLUMN = "free";
    public static final String PRICE_COLUMN = "price";
    public static final String ROOMS_TABLE_NAME= "rooms";
    public static final String BLOCK_TABLE_NAME = "blocks";
    public static final String NAME_COLUMN_IN_BLOCK = "name";
    public static final String BLOCK_ID_IN_ROOMS = "block_id";
    public static final String DATE ="date";
    private static int VERSION = 2;


    public DatabaseProviderImpl(Context context){
        cntx = context;
        dh = new MyDataBaseProvider(cntx,"MARKET_PLACE",null,2);
    }


    public void addRooms(Room room){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NUMBER_COLUMN,room.getNumber());
        contentValues.put(WATER_COLUMN,room.getWater());
        contentValues.put(FREE_COLUMN,room.getFree());
        contentValues.put(PRICE_COLUMN,room.getPrice());
        contentValues.put(BLOCK_ID_IN_ROOMS,room.getBlock_id());
        contentValues.put(DATE,room.getDate());
        db.insert(ROOMS_TABLE_NAME,null,contentValues);
        db.close();
    }

    public void addBlocks(Block block){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_IN_BLOCK,block.getName());
        db.insert(BLOCK_TABLE_NAME,null,contentValues);
        db.close();
    }

    public ArrayList<Block> selectBlocksFromId(int id){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Block> arr = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM "+BLOCK_TABLE_NAME+ " WHERE _id = ?;",new String[]{""+id});
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int nameIndex = c.getColumnIndex(NAME_COLUMN_IN_BLOCK);

            do {
                Block block = new Block(c.getInt(idIndex), c.getString(nameIndex));
                arr.add(block);
            } while (c.moveToNext());
        } else {
            Log.d("!!!!!!", "0 rows");
            c.close();
        }
        if (!c.isClosed()) {
            c.close();
        }
        db.close();
        return arr;
    }

    public ArrayList<Block> selectBlocks(){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Block> arr = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + BLOCK_TABLE_NAME+";",null);
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int nameIndex = c.getColumnIndex(NAME_COLUMN_IN_BLOCK);

            do {
                Block block = new Block(c.getInt(idIndex), c.getString(nameIndex));
                arr.add(block);
            } while (c.moveToNext());
        } else {
            Log.d("!!!!!!", "0 rows");
            c.close();
        }
        if (!c.isClosed()) {
            c.close();
        }
        db.close();
        return arr;
    }

    public ArrayList<Room> selectRoomsWihtBlockId(int id){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Room> arr = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " +ROOMS_TABLE_NAME+ " WHERE "+BLOCK_ID_IN_ROOMS+" = ?;",new String[]{""+id});
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int number_index = c.getColumnIndex(NUMBER_COLUMN);
            int waterIndex = c.getColumnIndex(WATER_COLUMN);
            int freeIndex = c.getColumnIndex(FREE_COLUMN);
            int priceIndex = c.getColumnIndex(PRICE_COLUMN);
            int block_idIndex = c.getColumnIndex(BLOCK_ID_IN_ROOMS);
            int date_index = c.getColumnIndex(DATE);

            do {
                Room room = new Room(c.getInt(idIndex),c.getInt(number_index),c.getInt(waterIndex),c.getInt(freeIndex)
                        ,c.getInt(priceIndex),c.getInt(block_idIndex),c.getString(date_index));
                arr.add(room);

            } while (c.moveToNext());
        } else {
            c.close();
        }
        if (!c.isClosed()) {
            c.close();
        }
        db.close();
        return arr;
    }

    public ArrayList<Room> selectRooms(){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Room> arr = new ArrayList<>();
        Cursor c = db.query(ROOMS_TABLE_NAME,null,null,null,null,null,null);
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int numberIndex = c.getColumnIndex(NUMBER_COLUMN);
            int waterIndex = c.getColumnIndex(WATER_COLUMN);
            int freeIndex = c.getColumnIndex(FREE_COLUMN);
            int priceIndex = c.getColumnIndex(PRICE_COLUMN);
            int block_idIndex = c.getColumnIndex(BLOCK_ID_IN_ROOMS);
            int date_index = c.getColumnIndex(DATE);

            do {
                Room room = new Room(c.getInt(idIndex),c.getInt(numberIndex), c.getInt(waterIndex),
                        c.getInt(freeIndex),c.getInt(priceIndex),c.getInt(block_idIndex),
                        c.getString(date_index));
                arr.add(room);


            } while (c.moveToNext());
        } else {
            c.close();
        }
        if (!c.isClosed()) {
            c.close();
        }
        db.close();
        return arr;
    }


    public void deleteRooms(){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(ROOMS_TABLE_NAME,null,null);
        db.close();}

    public void deleteBlocks(){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(BLOCK_TABLE_NAME,null,null);
        db.close();
        deleteRooms();
    }

    public void updateRoomsNumber(int id,int water){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NUMBER_COLUMN,water);
        db.update(ROOMS_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateRoomsWater(int id,int water){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WATER_COLUMN,water);
        db.update(ROOMS_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateRoomsPrice(int id,int price){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRICE_COLUMN,price);
        db.update(ROOMS_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateRoomsDate(int id,String date){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATE,date);
        db.update(ROOMS_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateRoomsFree(int id,int free){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FREE_COLUMN,free);
        db.update(ROOMS_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }


    public void updateBlocks(int id,String name){
        SQLiteDatabase db = dh.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN_IN_BLOCK,name);
        db.update(BLOCK_TABLE_NAME,cv,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllRoomsInBloc(int block_id){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(ROOMS_TABLE_NAME,BLOCK_ID_IN_ROOMS + " = ?",new String[]{String.valueOf(block_id)});
        db.close();
    }

    public void deleteOneRoomFromId(int id){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(ROOMS_TABLE_NAME,"_id" + " = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteOneBlockFromId(int id){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(BLOCK_TABLE_NAME,"_id = ?",new String[]{String.valueOf(id)});
        db.close();
        deleteAllRoomsInBloc(id);
    }
}
