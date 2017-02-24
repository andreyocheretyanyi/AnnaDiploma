package ua.com.anna.borodina.annadiploma.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IBlockDetailView;

public class BlockDetailView extends AppCompatActivity implements IBlockDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_detail_view);
    }
}
