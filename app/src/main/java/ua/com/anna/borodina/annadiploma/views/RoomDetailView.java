package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.interfaces.IRoomDetailView;

public class RoomDetailView extends AppCompatActivity implements IRoomDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_view);
        Button back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Context getContextFromView() {
        return this;
    }
}
