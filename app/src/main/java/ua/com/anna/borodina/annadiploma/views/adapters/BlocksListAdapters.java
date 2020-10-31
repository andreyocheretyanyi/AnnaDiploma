package ua.com.anna.borodina.annadiploma.views.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.dao.Block;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.fragments.BaseFragment;


public class BlocksListAdapters extends RecyclerView.Adapter<BlocksListAdapters.ViewHolder> {

    private Context context;
    private BlockListPresenter presenter;
    private BaseFragment parent;

    public BlocksListAdapters(Context context, BaseFragment parent) {
        this.context = context;
        this.parent = parent;
    }

    private ArrayList<Block> myDataSet = new ArrayList<>();

    public void bindPresenter(BlockListPresenter presenter) {
        this.presenter = presenter;
    }

    public void setData(ArrayList<Block> newData) {
        myDataSet = newData;
        notifyDataSetChanged();
    }

    public void addItem(Block obj) {
        myDataSet.add(obj);
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        presenter.deleteBlock(myDataSet.get(pos).getId());
        myDataSet.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, myDataSet.size());
    }

    public void clear() {
        int size = myDataSet.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                myDataSet.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_blocks, parent, false);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = position;
        holder.blockName.setText(myDataSet.get(position).getName());
        holder.blockNumber.setText((position + 1) + ".");


        holder.buttonListDetail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.isOpen) {
                    holder.isOpen = true;
                    holder.roomListAdapter.setData(presenter.getRoomFromBlockId(myDataSet.get(pos).getId()));
                    holder.roomListAdapter.notifyDataSetChanged();
                    holder.roomList.setVisibility(View.VISIBLE);

                } else {
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

    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private RoomListAdapter roomListAdapter;
        private LayoutManager lm;
        @BindView(R.id.block_name)
        TextView blockName;
        @BindView(R.id.block_number_text_view)
        TextView blockNumber;
        @BindView(R.id.button_delete_block)
        Button deleteButton;
        @BindView(R.id.recycler_room_list)
        public RecyclerView roomList;
        @BindView(R.id.button_detail)
        Button buttonListDetail;
        boolean isOpen = false;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            roomList = (RecyclerView) roomList.findViewById(R.id.recycler_room_list);
            roomListAdapter = new RoomListAdapter(itemView.getContext(), parent);
            lm = new LinearLayoutManager(itemView.getContext());
            roomList.setAdapter(roomListAdapter);
            roomList.setLayoutManager(lm);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == deleteButton.getId()) {
                removeItem(getAdapterPosition());
            }
        }
    }
}
