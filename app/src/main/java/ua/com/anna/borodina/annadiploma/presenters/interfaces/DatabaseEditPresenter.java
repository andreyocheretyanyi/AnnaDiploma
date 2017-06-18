package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import java.util.List;

import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;



public interface DatabaseEditPresenter extends BasePresenter<IDatabaseEdit> {

    void addBlock(String name);

    void clearAll();

    public void addRoom(Boolean water, String number, String price, Boolean free, String block_id,String date);

    public List<String> getBlockNameArray();

    public void sendNotification();

}
