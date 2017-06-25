package ua.com.anna.borodina.annadiploma.presenters;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.com.anna.borodina.annadiploma.App;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.GetDataResultResponse;
import ua.com.anna.borodina.annadiploma.model.dao.Notification;
import ua.com.anna.borodina.annadiploma.model.dao.ResponseStatusUpdate;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.model.dao.SendMessageRequest;
import ua.com.anna.borodina.annadiploma.model.dao.UpdateRequestBody;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;



public class DatabaseEditPresenterImpl implements DatabaseEditPresenter {

    private IDatabaseEdit view;


    @Override
    public void onAttachView(IDatabaseEdit view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {

    }


    public void createRequestForUpdate(final RelativeLayout relativeLayout){
        relativeLayout.setVisibility(View.VISIBLE);
        UpdateRequestBody updateRequestBody = new UpdateRequestBody();
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        List<Block> blocks =  dp.selectBlocks();
        List<Room> rooms = dp.selectRooms();
        for (Room room : rooms){
            Log.d("ROOOOOOOMS",room.getId()+"");
        }

        updateRequestBody.setBlocks(blocks);
        updateRequestBody.setRooms(rooms);
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-type:","application/json");
        headers.put("Token:","");
        App.getRetrofitServer().updateDataOnServer(headers,updateRequestBody).enqueue(new Callback<ResponseStatusUpdate>() {
            @Override
            public void onResponse(Call<ResponseStatusUpdate> call, Response<ResponseStatusUpdate> response) {
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseStatusUpdate> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
            }
        });
    }


    public void getDataFromServer(final RelativeLayout relativeLayout){
        relativeLayout.setVisibility(View.VISIBLE);
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-type:","application/json");
        headers.put("Token:","");
        App.getRetrofitServer().getDataFromServer(headers).enqueue(new Callback<GetDataResultResponse>() {
            @Override
            public void onResponse(Call<GetDataResultResponse> call, Response<GetDataResultResponse> response) {
                if(response.body().getStatus()) {
                    DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
                    dp.deleteBlocks();
                    for (Block block : response.body().getBlocks())
                        dp.addBlocks(block);
                    for (Room room : response.body().getRooms())
                        dp.addRooms(room);
                }
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetDataResultResponse> call, Throwable t) {
                relativeLayout.setVisibility(View.GONE);
            }
        });

    }

    public void addRoom(Boolean water,String number, String price, Boolean free, String block_id,String date) {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        dp.addRooms(validate(water,number, price,free,block_id,date));
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

    private Room validate(Boolean water, String number, String price, Boolean free, String blockId, String date){
        Room room;
        int water_int,price_int,free_int,block_id_int,number_int;
        water_int =validateWaterOrFree(water);
        price_int = Integer.parseInt(price);
        free_int = validateWaterOrFree(free);
        block_id_int = getBlockId(blockId);
        number_int = Integer.parseInt(number);
        room = new Room();
        room.setId(0);
        room.setBlockId(block_id_int);
        room.setNumber(number_int);
        room.setPrice(price_int);
        room.setFree(free_int);
        room.setWater(water_int);
        room.setDate(date);
        return room;

    }

    public void sendNotification(){
        SendMessageRequest msg =new SendMessageRequest();
        Notification notification = new Notification();
        notification.setTitle("База данных была изменена");
        notification.setBody("Внимание! База данных была изменена");
        msg.setNotification(notification);
        msg.setTo("/topics/allDevices");
        App.getRetrofitNotifications().authRequest(msg).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void addBlock(String name) {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        Block block = new Block();
        block.setId(0);
        block.setName(name);
        dp.addBlocks(block);
    }


    public void clearAll() {
        DatabaseProviderImpl dp = new DatabaseProviderImpl(view.getContextFromView());
        dp.deleteBlocks();
    }
}
