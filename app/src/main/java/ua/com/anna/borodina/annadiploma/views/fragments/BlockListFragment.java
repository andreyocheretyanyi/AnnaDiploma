package ua.com.anna.borodina.annadiploma.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.BlockListViewPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.BlockListPresenter;
import ua.com.anna.borodina.annadiploma.views.adapters.BlocksListAdapters;
import ua.com.anna.borodina.annadiploma.views.dialogs.DialogWithMap;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockListVIew;



public class BlockListFragment extends BaseFragment implements IBlockListVIew {
  private RecyclerView recyclerView;
  private BlocksListAdapters mAdapter;
  private BlockListPresenter presenter;
  private LinearLayoutManager mLayoutManager;
  private DialogWithMap mDialogWithMap;
  @BindView(R.id.fab_show_map)
  FloatingActionButton mShowMap;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_blocks_list_view,container,false);
    ButterKnife.bind(this,v);
    initViews(v);
    createDialog();
    return v;

  }

  @OnClick(R.id.fab_show_map)
  public void onClickShowMap(){
    showDialog();
  }

  private void initViews(View v){
    presenter = new BlockListViewPresenterImpl();
    presenter.onAttachView(this);
    recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_in_block_list);
    mAdapter = new BlocksListAdapters(v.getContext());
    mAdapter.setData(presenter.getData());
    mAdapter.notifyDataSetChanged();
    mAdapter.bindPresenter(presenter);
    mLayoutManager = new LinearLayoutManager(v.getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(mAdapter);
  }

  private void createDialog(){
      mDialogWithMap = new DialogWithMap();
  }

  private void showDialog(){
    mDialogWithMap.show(getChildFragmentManager(),"showMapInList");
  }

  @Override
  public Context getContextFromView() {
    return getContext();
  }
}

