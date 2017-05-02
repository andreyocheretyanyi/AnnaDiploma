package ua.com.anna.borodina.annadiploma.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockDetailView;

/**
 * Created by Андрей on 01.05.2017.
 */

public class BlockDetailFragment extends BaseFragment implements IBlockDetailView {


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
     Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_block_detail_view,container,false);
  }

  @Override
  public Context getContextFromView() {
    return getContext();
  }
}
