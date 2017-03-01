package ua.com.anna.borodina.annadiploma.model.dao;

/**
 * Created by Андрей on 21.02.2017.
 */

public class Room {

    private int id,price,water,block_id,free;

    public Room(int id, int water, int free, int price, int block_id) {
        this.id = id;
        this.price = price;
        this.water = water;
        this.block_id = block_id;
        this.free = free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

}
