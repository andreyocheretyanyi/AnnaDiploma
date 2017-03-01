package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;

/**
 * Created by Андрей on 26.02.2017.
 */

public interface DatabaseEditPresenter extends BasePresenter<IDatabaseEdit> {

    void addBlock(String name);

    void clearAll();

    public void addRoom(String water, String price, String free, String block_id);

}
