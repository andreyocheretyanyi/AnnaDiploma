package ua.com.anna.borodina.annadiploma.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.BlockListViewPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.adapters.BlocksListAdapters;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;

/**
 * Created by Андрей on 01.05.2017.
 */

public class BlockListFragment extends BaseFragment implements IBlockListVIew {
  private RecyclerView recyclerView;
  private BlocksListAdapters mAdapter;
  private BlockListPresenter presenter;
  private LinearLayoutManager mLayoutManager;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_blocks_list_view,container,false);
    initViews(v);
    return v;

  }

  private void initViews(View v){
    presenter = new BlockListViewPresenterImpl();
    presenter.onAttachView(this);
    recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_in_block_list);
    mAdapter = new BlocksListAdapters(v.getContext());
    mAdapter.setData(presenter.getData());
    mAdapter.notifyDataSetChanged();
    mLayoutManager = new LinearLayoutManager(v.getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(mAdapter);

  }

  @Override
  public Context getContextFromView() {
    return getContext();
  }
}

