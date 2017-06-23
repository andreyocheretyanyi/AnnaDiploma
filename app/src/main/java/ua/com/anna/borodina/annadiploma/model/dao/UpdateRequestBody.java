package ua.com.anna.borodina.annadiploma.model.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 23.06.2017.
 */

public class UpdateRequestBody {

    @SerializedName("blocks")
    @Expose
    private List<Block> blocks = null;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
