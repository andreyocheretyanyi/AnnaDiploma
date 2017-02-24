package ua.com.anna.borodina.annadiploma.presenters;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;

/**
 * Created by admin on 24.02.2017.
 */

public class BlockListViewPresenterImpl implements BlockListPresenter {

    @Override
    public void onAttachView(IBlockListVIew view) {

    }

    @Override
    public void onDetachView() {

    }

    @Override
    public ArrayList<Block> getData() {
        ArrayList<Block> arr = new ArrayList<>();
        for (int i = 0; i < 200; i++)
        arr.add(new Block("Block "+i));
        return arr;
    }
}
