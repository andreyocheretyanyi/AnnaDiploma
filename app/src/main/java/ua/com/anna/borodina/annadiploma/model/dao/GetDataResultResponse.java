
package ua.com.anna.borodina.annadiploma.model.dao;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDataResultResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("blocks")
    @Expose
    private List<Block> blocks = null;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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
