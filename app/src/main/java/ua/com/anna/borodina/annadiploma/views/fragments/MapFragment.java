package ua.com.anna.borodina.annadiploma.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.github.barteksc.pdfviewer.PDFView;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IMapView;

/**
 * Created by Андрей on 01.05.2017.
 */

public class MapFragment extends BaseFragment implements IMapView {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_map_view,container,false);
    PDFView pdfView = (PDFView) v.findViewById(R.id.pdfView);
    pdfView.fromAsset("map.pdf").load();
    pdfView.setMaxZoom(20);
    pdfView.setMidZoom(10);
    pdfView.setMinZoom(1);
    pdfView.zoomTo(1);
    return v;
  }


  @Override
  public Context getContextFromView() {
    return getContext();
  }
}
