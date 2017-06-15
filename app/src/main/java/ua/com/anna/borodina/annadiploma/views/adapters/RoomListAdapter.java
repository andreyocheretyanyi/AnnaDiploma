package ua.com.anna.borodina.annadiploma.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;



public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

  private Context context;
  private DatabaseProviderImpl db;

  public RoomListAdapter(Context context) {
    this.context = context;
  }

  private ArrayList<Room> myDataSet = new ArrayList<>();

  public void setData( ArrayList<Room> newData) {
    myDataSet = newData;
    notifyDataSetChanged();
  }

  public void addItem(Room obj) {
    myDataSet.add(obj);
    notifyDataSetChanged();
  }

  public void removeItem(int pos) {
    db.deleteOneRoomFromId(myDataSet.get(pos).getId());
    myDataSet.remove(pos);
    notifyItemRemoved(pos);
    notifyItemRangeChanged(pos, myDataSet.size());
  }

  public void clear() {
    int size = myDataSet.size();
    if (size > 0) {
      for (int i =0; i < size; i++) {
        myDataSet.remove(0);
      }
      notifyItemRangeRemoved(0, size);
    }
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.item_rooms, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
   final int pos = position;
      db = new DatabaseProviderImpl(context);
      holder.roomNumber.setText(String.valueOf(myDataSet.get(pos).getNumber()));
      holder.roomPrice.setText(String.valueOf(myDataSet.get(pos).getPrice()));
      holder.roomFree.setChecked(validateWaterOrFree(myDataSet.get(pos).getFree()));
      holder.roomWater.setChecked(validateWaterOrFree(myDataSet.get(pos).getWater()));

      holder.changeRoomButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int id = myDataSet.get(pos).getId();
          int price = validateOtherData(holder.roomPrice);
          int number = validateOtherData(holder.roomNumber);
          if(price != -1 && number != -1) {
            db.updateRoomsPrice(id, price);
            db.updateRoomsNumber(id,number);
            db.updateRoomsFree(id, validateWaterOrFree(holder.roomFree.isChecked()));
            db.updateRoomsWater(id, validateWaterOrFree(holder.roomWater.isChecked()));
          }
        }
      });
  }

  private boolean validateWaterOrFree(int flag){
    if(flag == 1){
      return true;
    }else return false;
  }

  private int validateWaterOrFree(Boolean bool){
    if(bool)
      return 1;
    else return 0;
  }

  private int validateOtherData(EditText editWithData){
    int data;
    try {
      data = Integer.parseInt(editWithData.getText().toString());
      return data;
    }catch (Exception e){
      editWithData.setError("Ошибка ввода данных");
      return -1;
    }
  }

  @Override
  public int getItemCount() {
    return myDataSet.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.button_change_room) Button changeRoomButton;
    @BindView(R.id.edit_text_room_number) EditText roomNumber;
    @BindView(R.id.edit_text_price) EditText roomPrice;
    @BindView(R.id.switch_free)
    SwitchCompat roomFree;
    @BindView(R.id.switch_water)
    SwitchCompat roomWater;
    @BindView(R.id.button_delete_room)
    Button deleteRoom;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
      deleteRoom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
      if(v.getId() == deleteRoom.getId()){
        removeItem(getAdapterPosition());
      }
    }
  }
}