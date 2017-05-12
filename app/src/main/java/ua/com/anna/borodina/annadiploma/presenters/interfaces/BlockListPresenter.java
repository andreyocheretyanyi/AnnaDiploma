package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;

/**
 * Created by admin on 24.02.2017.
 */

public interface BlockListPresenter extends BasePresenter<IBlockListVIew> {

    public ArrayList<Block> getData();

    public ArrayList<Room> getRoomFromBlockId(int id);
}
