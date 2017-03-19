package ua.com.anna.borodina.annadiploma.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;


/**
 * Created by Андрей on 19.02.2017.
 */

public class DatabaseProviderImpl {
    private Context cntx;
    private MyDataBaseProvider dh;

    public DatabaseProviderImpl(Context context){
        cntx = context;
        dh = new MyDataBaseProvider(cntx,"MARKET_PLACE",null,1);
    }


    public void addRooms(Room room){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("water",room.getWater());
        contentValues.put("free",room.getFree());
        contentValues.put("price",room.getPrice());
        contentValues.put("block_id",room.getBlock_id());
        db.insert("rooms",null,contentValues);
        db.close();
    }

    public void addBlocks(Block block){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",block.getName());
        db.insert("blocks",null,contentValues);
        db.close();
    }


    public ArrayList<Block> selectBlocks(){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Block> arr = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM blocks;",null);
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int nameIndex = c.getColumnIndex("name");

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
        Cursor c = db.rawQuery("SELECT * FROM rooms WHERE block_id = ?;",new String[]{""+id});
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int waterIndex = c.getColumnIndex("water");
            int freeIndex = c.getColumnIndex("free");
            int priceIndex = c.getColumnIndex("price");
            int block_idIndex = c.getColumnIndex("block_id");

            do {
                Room room = new Room(c.getInt(idIndex), c.getInt(waterIndex),c.getInt(freeIndex),c.getInt(priceIndex),c.getInt(block_idIndex));
                arr.add(room);
                Log.d("!!!!!!",
                        "ID = " + c.getInt(idIndex) +
                                ", water = " + c.getString(waterIndex));

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

    public ArrayList<Room> selectRooms(){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Room> arr = new ArrayList<>();
        Cursor c = db.query("rooms",null,null,null,null,null,null);
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex("_id");
            int waterIndex = c.getColumnIndex("water");
            int freeIndex = c.getColumnIndex("free");
            int priceIndex = c.getColumnIndex("price");
            int block_idIndex = c.getColumnIndex("block_id");

            do {
                Room room = new Room(c.getInt(idIndex), c.getInt(waterIndex),c.getInt(freeIndex),c.getInt(priceIndex),c.getInt(block_idIndex));
                arr.add(room);
                Log.d("!!!!!!",
                        "ID = " + c.getInt(idIndex) +
                                ", water = " + c.getString(waterIndex) +
                                ", block = " + c.getString(block_idIndex));


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


    public void deleteRooms(){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete("rooms",null,null);
        db.close();}

    public void deleteBlocks(){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete("blocks",null,null);
        db.close();
    }

    public void updateRooms(){}

    public void updateBlocks(){}
}
