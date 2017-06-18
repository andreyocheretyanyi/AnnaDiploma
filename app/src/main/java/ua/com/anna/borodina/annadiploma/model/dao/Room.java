package ua.com.anna.borodina.annadiploma.model.dao;



public class Room {

    private int id,price,water,block_id,free,number;
    private String date;

    public Room(int id, int number, int water, int free, int price, int block_id,String date) {

        this.id = id;
        this.price = price;
        this.water = water;
        this.block_id = block_id;
        this.free = free;
        this.number = number;
        this.date = date;
    }


    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumber(int number) {
        this.number = number;
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
