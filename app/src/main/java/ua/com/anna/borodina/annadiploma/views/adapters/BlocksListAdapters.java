package ua.com.anna.borodina.annadiploma.views.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;

/**
 * Created by admin on 24.02.2017.
 */

public class BlocksListAdapters extends RecyclerView.Adapter<BlocksListAdapters.ViewHolder> {

    private Context context;
    private BlockListPresenter presenter;

    public BlocksListAdapters(Context context) {
        this.context = context;
    }

    private ArrayList<Block> myDataSet = new ArrayList<>();

    public void bindPresenter(BlockListPresenter presenter){
        this.presenter = presenter;
    }

    public void setData( ArrayList<Block> newData) {
        myDataSet = newData;
        notifyDataSetChanged();
    }

    public void addItem(Block obj) {
        myDataSet.add(obj);
        notifyDataSetChanged();
    }

    public void removeItem(Block obj) {
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
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_blocks_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       final int pos = position;
        holder.blockName.setText(myDataSet.get(position).getName());
        holder.blockNumber.setText((position+1)+".");
      holder.buttonListDetail.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(!holder.isOpen){
            holder.isOpen = true;
            holder.roomListAdapter.setData(presenter.getRoomFromBlockId(myDataSet.get(pos).getId()));
            holder.roomListAdapter.notifyDataSetChanged();
            holder.roomList.setVisibility(View.VISIBLE);
          }
          else {
            holder.isOpen = false;
            holder.roomList.setVisibility(View.GONE);
          }
        }
      });

    }


    @Override
    public int getItemCount() {
        return myDataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
       private RoomListAdapter roomListAdapter;
       private LayoutManager lm;
        @BindView(R.id.block_name)
        TextView blockName;
        @BindView(R.id.block_number_text_view)
        TextView blockNumber;
        @BindView(R.id.recycler_room_list)
        RecyclerView roomList;
      @BindView(R.id.button_detail)
      Button buttonListDetail;
        boolean isOpen = false;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            roomList = (RecyclerView) roomList.findViewById(R.id.recycler_room_list);
            roomListAdapter = new RoomListAdapter(itemView.getContext());
            lm = new LinearLayoutManager(itemView.getContext());
            roomList.setAdapter(roomListAdapter);
            roomList.setLayoutManager(lm);

        }

    }
}
