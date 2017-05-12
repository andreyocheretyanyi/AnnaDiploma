package ua.com.anna.borodina.annadiploma.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;

/**
 * Created by Андрей on 12.05.2017.
 */

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

  private Context context;
  private DatabaseProviderImpl db;

  public RoomListAdapter(Context context) {
    this.context = context;
  }

  int position = 0;
  private ArrayList<Room> myDataSet = new ArrayList<>();

  public void setData( ArrayList<Room> newData) {
    myDataSet = newData;
    notifyDataSetChanged();
  }

  public void addItem(Room obj) {
    myDataSet.add(obj);
    notifyDataSetChanged();
  }

  public void removeItem(Room obj) {
    myDataSet.remove(obj);
    notifyDataSetChanged();
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
  public void onBindViewHolder(ViewHolder holder, int position) {
      db = new DatabaseProviderImpl(context);
      holder.roomBlockName.setText(String.valueOf(db.selectBlocksFromId(myDataSet.get(position).getBlock_id()).get(0).getName()));
      holder.roomNumber.setText(String.valueOf(myDataSet.get(position).getNumber()));

  }



  @Override
  public int getItemCount() {
    return myDataSet.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.text_view_room_block_name) TextView roomBlockName;
    @BindView(R.id.text_view_room_number) TextView roomNumber;
    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);

    }
  }
}