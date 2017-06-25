package ua.com.anna.borodina.annadiploma.model.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("block_id")
    @Expose
    private Integer blockId;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("free")
    @Expose
    private Integer free;
    @SerializedName("water")
    @Expose
    private Integer water;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
