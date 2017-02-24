package ua.com.anna.borodina.annadiploma.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IRoomDetailView;

public class RoomDetailView extends AppCompatActivity implements IRoomDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_view);
    }
}
