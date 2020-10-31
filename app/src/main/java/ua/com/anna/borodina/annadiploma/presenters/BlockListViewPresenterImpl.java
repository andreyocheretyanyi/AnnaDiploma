package ua.com.anna.borodina.annadiploma.presenters;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;


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

    public ArrayList<Room> getRoomFromBlockId(int id) {
        DatabaseProviderImpl provider = new DatabaseProviderImpl(view.getContextFromView());
        return provider.selectRoomsWihtBlockId(id);
    }

    @Override
    public void deleteBlock(int id) {
        DatabaseProviderImpl provider = new DatabaseProviderImpl(view.getContextFromView());
        provider.deleteOneBlockFromId(id);
    }

}
