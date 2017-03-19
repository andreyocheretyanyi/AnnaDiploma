package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.model.dao.Room;
import ua.com.anna.borodina.annadiploma.presenters.DatabaseEditPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;

public class DatabaseEditActivity extends AppCompatActivity implements IDatabaseEdit {

    @BindView(R.id.button_save_room) Button mSaveButton;
    @BindView(R.id.switch_water) Switch mWaterSwitch;
    @BindView(R.id.switch_free) Switch mFreeSwithc;
    @BindView(R.id.spinner_room_block_list) Spinner mBlockListSpinner;
    @BindView(R.id.edit_text_room_price) EditText mPriceEditText;
    @BindView(R.id.edit_text_block_name) EditText mBlockName;
    private DatabaseEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_edit);
        presenter = new DatabaseEditPresenterImpl();
        presenter.onAttachView(this);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");
        initViews();
    }

    @OnClick(R.id.button_add_block)
    public void addBlockClick(){
        String str = mBlockName.getText().toString();
        if(str.equals(""))
            mBlockName.setError("Поле не должно быть пустым");
        else {
            presenter.addBlock(str);
            mBlockName.setText("");
            presenter.sendNotification();
            this.recreate();
        }
    }

    @OnClick(R.id.button_save_room)
    public void saveRoomClick(){
        if(mPriceEditText.getText().toString().equals(""))
            mPriceEditText.setError("Поле не должно быть пустым");
        else {
            presenter.sendNotification();
            presenter.addRoom(mWaterSwitch.isChecked(), mPriceEditText.getText().toString(), mFreeSwithc.isChecked(), (String) mBlockListSpinner.getSelectedItem());
        }
    }

    @OnClick(R.id.delete_all)
    public void deleteAllClick(){
        presenter.clearAll();
        presenter.sendNotification();
    }

    ArrayAdapter<String> adapter;

    private void initViews(){
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.getBlockNameArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBlockListSpinner.setAdapter(adapter);
    }


    @Override
    public Context getContextFromView() {
        return this;
    }
}
