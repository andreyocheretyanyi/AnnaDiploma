package ua.com.anna.borodina.annadiploma.presenters;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;

/**
 * Created by admin on 24.02.2017.
 */

public class BlockListViewPresenterImpl implements BlockListPresenter {

    private IBlockListVIew view;

    @Override
    public void onAttachView(IBlockListVIew view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {

    }

    @Override
    public ArrayList<Block> getData() {
        DatabaseProviderImpl provider = new DatabaseProviderImpl(view.getContextFromView());
        return provider.selectBlocks();
    }

    public ArrayList<Room> getRoomFromBlockId(int id){
        DatabaseProviderImpl provider = new DatabaseProviderImpl(view.getContextFromView());
        return provider.selectRoomsWihtBlockId(id);
    }
}
