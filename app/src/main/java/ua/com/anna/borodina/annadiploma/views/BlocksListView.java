package ua.com.anna.borodina.annadiploma.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.BlockListViewPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.adapters.BlocksListAdapters;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;

public class BlocksListView extends AppCompatActivity implements IBlockListVIew {

    private RecyclerView recyclerView;
    private BlocksListAdapters mAdapter;
    private BlockListPresenter presenter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks_list_view);
        initViews();
    }

    private void initViews(){
        presenter = new BlockListViewPresenterImpl();
        presenter.onAttachView(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_in_block_list);
        mAdapter = new BlocksListAdapters(this);
        mAdapter.setData(presenter.getData());
        mAdapter.notifyDataSetChanged();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }
}
