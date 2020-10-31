package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import android.os.AsyncTask;
import android.widget.RelativeLayout;

import java.util.List;

import ua.com.anna.borodina.annadiploma.model.dao.UpdateRequestBody;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;


public interface DatabaseEditPresenter extends BasePresenter<IDatabaseEdit> {

    public AsyncTask<Void,Void,UpdateRequestBody> createRequestForUpdate(RelativeLayout relativeLayout);

    public void getDataFromServer(RelativeLayout relativeLayout);

    void addBlock(String name);

    void clearAll();

    public void addRoom(Boolean water, String number, String price, Boolean free, String block_id, String date);

    public List<String> getBlockNameArray();

    public void sendNotification();

}
