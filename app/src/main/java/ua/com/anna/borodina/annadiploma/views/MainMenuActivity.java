package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.MainMenuPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.MainMenuPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IMainMenuView;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuView,View.OnClickListener {

    private MainMenuPresenter mainMenuPresenter;
    private LinearLayout map,selectBlocks,aboutAuthor,editData,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initView();

    }

    //    DatabaseProviderImpl dbHelper = new DatabaseProviderImpl(this);
//    Cursor c = dbHelper.selectBlocks();
//if(c.moveToFirst()) {
//        int idIndex = c.getColumnIndex("id");
//        int nameIndex = c.getColumnIndex("name");
//
//        do {
//        Log.d("!!!!!!",
//        "ID = " + c.getInt(idIndex) +
//        ", name = " + c.getString(nameIndex));
//
//        } while (c.moveToNext());
//        }
//        else {
//        Log.d("!!!!!!", "0 rows");
//        c.close();
//        }
//        if(!c.isClosed()){
//        c.close();
//        }
//        dbHelper.deleteBlocks();
//
//        c = dbHelper.selectBlocks();
//        if(c.moveToFirst()) {
//        int idIndex = c.getColumnIndex("id");
//        int nameIndex = c.getColumnIndex("name");
//
//        do {
//        Log.d("!!!!!!",
//        "ID = " + c.getInt(idIndex) +
//        ", name = " + c.getString(nameIndex));
//
//        } while (c.moveToNext());
//        }
//        else {
//        Log.d("!!!!!!", "0 rows");
//        c.close();
//        }
//        if(!c.isClosed()){
//        c.close();
//        }
//
//        //-----------------------------------------------
//
//
//
//        c = dbHelper.selectRooms();
//        if(c.moveToFirst()) {
//        int idIndex = c.getColumnIndex("id");
//        int waterIndex = c.getColumnIndex("water");
//        int freeIndex = c.getColumnIndex("free");
//        int priceIndex = c.getColumnIndex("price");
//        int block_idIndex = c.getColumnIndex("block_id");
//
//        do {
//        Log.d("!!!!!!",
//        "ID = " + c.getInt(idIndex) +
//        ", water = " + c.getString(waterIndex)
//        +
//        ", free = " + c.getString(freeIndex)
//        +
//        ", price = " + c.getString(priceIndex)
//        +
//        ", block_id = " + c.getString(block_idIndex));
//
//        } while (c.moveToNext());
//        }
//        else {
//        Log.d("!!!!!!", "0 rows");
//        c.close();
//        }
//        if(!c.isClosed()){
//        c.close();
//        }


    private void initView(){
        map = (LinearLayout) findViewById(R.id.map_line);
        selectBlocks = (LinearLayout) findViewById(R.id.select_line);
        aboutAuthor = (LinearLayout) findViewById(R.id.line_about);
        editData = (LinearLayout) findViewById(R.id.line_change_database);
        exit = (LinearLayout) findViewById(R.id.exit_line);
        mainMenuPresenter = new MainMenuPresenterImpl();
        mainMenuPresenter.onAttachView(this);
        map.setOnClickListener(this);
        selectBlocks.setOnClickListener(this);
        aboutAuthor.setOnClickListener(this);
        editData.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_line:
                startActivity(new Intent(this,MapView.class));
                break;
            case R.id.select_line:
                startActivity(new Intent(this,BlocksListView.class));
                break;
            case R.id.line_about:
                startActivity(new Intent(this,BlocksListView.class));
                break;
            case R.id.line_change_database:
                startActivity(new Intent(this,DatabaseEditActivity.class));
                break;
            case R.id.exit_line:
                finish();
                break;
        }
    }

    @Override
    public Context getContextFromView() {
        return this;
    }
}
