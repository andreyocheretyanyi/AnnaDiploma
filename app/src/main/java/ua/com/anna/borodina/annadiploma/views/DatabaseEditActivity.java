package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.presenters.DatabaseEditPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;

public class DatabaseEditActivity extends AppCompatActivity implements IDatabaseEdit {

    EditText block_name,room_wate,room_price,room_free,room_block_id;
    Button addRoom,addBlock,clearAllBlocks;
    private DatabaseEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_edit);
        initViews();
    }


    private void initViews(){
        clearAllBlocks = (Button) findViewById(R.id.delete_all);
        block_name = (EditText) findViewById(R.id.block_name);
        presenter = new DatabaseEditPresenterImpl();
        presenter.onAttachView(this);
        addBlock = (Button) findViewById(R.id.save_block_btn);
        addBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addBlock(block_name.getText().toString());
            }
        });

        clearAllBlocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearAll();
            }
        });

        addRoom = (Button) findViewById(R.id.save_room_btn);
        room_wate = (EditText) findViewById(R.id.room_water);
        room_price = (EditText) findViewById(R.id.room_price);
        room_free = (EditText) findViewById(R.id.room_free);
        room_block_id = (EditText) findViewById(R.id.room_block);
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addRoom(room_wate.getText().toString(),room_price.getText().toString(),room_free.getText().toString(),room_block_id.getText().toString());
            }
        });
    }


    @Override
    public Context getContextFromView() {
        return this;
    }
}
