package ua.com.anna.borodina.annadiploma.presenters;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;

/**
 * Created by Андрей on 26.02.2017.
 */

public class DatabaseEditPresenterImpl implements DatabaseEditPresenter {

    private IDatabaseEdit view;


    @Override
    public void onAttachView(IDatabaseEdit view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {

    }

    public void addRoom(Boolean water, String price, Boolean free, String block_id) {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        dp.addRooms(validate(water,price,free,block_id));
        dp.selectRooms();
    }

    public List<String> getBlockNameArray(){
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        ArrayList<Block> blocks = dp.selectBlocks();
        ArrayList<String> blocksName = new ArrayList<>();
        for (Block b: blocks) {
            blocksName.add(b.getName());
        }

        return blocksName;
    }

    private int getBlockId(String blockName) {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        ArrayList<Block> blocks = dp.selectBlocks();

        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (blockName.equals(b.getName())) {
                return b.getId();
            }
        }
        return -1;
    }

    private int validateWaterOrFree(Boolean bool){
            if(bool)
                return 1;
            else return 0;
    }

    private Room validate(Boolean water,String price,Boolean free, String blockId){
        Room room;
        int water_int,price_int,free_int,block_id_int;
        water_int =validateWaterOrFree(water);
        price_int = Integer.parseInt(price);
        free_int = validateWaterOrFree(free);
        block_id_int = getBlockId(blockId);
        room = new Room(0,water_int,free_int,price_int,block_id_int);
        return room;

    }

    @Override
    public void addBlock(String name) {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        Block block = new Block(0,name);
        dp.addBlocks(block);
    }


    public void clearAll() {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        dp.deleteBlocks();
    }
}
