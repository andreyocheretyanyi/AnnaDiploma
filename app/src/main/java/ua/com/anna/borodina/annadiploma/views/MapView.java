package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IMapView;

public class MapView extends AppCompatActivity implements IMapView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        Button back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("map.pdf").load();
    }

    @Override
    public Context getContextFromView() {
        return this;
    }
}
