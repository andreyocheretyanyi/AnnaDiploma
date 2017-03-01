package ua.com.anna.borodina.annadiploma.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.dao.Block;

/**
 * Created by admin on 24.02.2017.
 */

public class BlocksListAdapters extends RecyclerView.Adapter<BlocksListAdapters.ViewHolder> {

    private Context context;

    public BlocksListAdapters(Context context) {
        this.context = context;
    }

    int position = 0;
    private ArrayList<Block> myDataSet = new ArrayList<>();

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_blocks_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.blockName.setText(myDataSet.get(position).getName());
        holder.blockNumber.setText((position+1)+".");

    }



    @Override
    public int getItemCount() {
        return myDataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView blockName;
        TextView blockNumber;
        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            blockName = (TextView) view.findViewById(R.id.block_name);
            blockNumber = (TextView) view.findViewById(R.id.block_number_text_view);

        }
    }
}
