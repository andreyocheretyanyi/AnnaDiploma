package ua.com.anna.borodina.annadiploma.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.DatabaseEditPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.dialogs.DialogWithCalendar;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;


public class DatabaseEditFragment extends BaseFragment implements IDatabaseEdit {


    @BindView(R.id.switch_water)
    SwitchCompat switchWater;
    @BindView(R.id.switch_free)
    SwitchCompat switchFree;
    @BindView(R.id.edit_text_room_price)
    EditText editTextRoomPrice;
    @BindView(R.id.spinner_room_block_list)
    Spinner spinnerRoomBlockList;
    @BindView(R.id.button_save_room)
    Button buttonSaveRoom;
    @BindView(R.id.edit_text_block_name)
    EditText editTextBlockName;
    @BindView(R.id.button_add_block)
    Button buttonAddBlock;
    @BindView(R.id.delete_all)
    Button deleteAll;
    @BindView(R.id.activity_database_edit)
    RelativeLayout activityDatabaseEdit;
    @BindView(R.id.edit_text_room_number)
    EditText mRoomNumber;
    @BindView(R.id.text_view_dayoff)
    TextView mDayOffText;
    @BindView(R.id.dialog_wait)
    RelativeLayout mWaitDialog;
    Unbinder unbinder;
    private DatabaseEditPresenter presenter;
    DialogWithCalendar dialogWithCalendar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogWithCalendar = new DialogWithCalendar();
        dialogWithCalendar.bindParent(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_database_edit, container, false);
        presenter = new DatabaseEditPresenterImpl();
        presenter.onAttachView(this);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");
        unbinder = ButterKnife.bind(this, v);
        initViews(v);
        return v;
    }

    @OnClick(R.id.button_add_block)
    public void addBlockClick() {
        String str = editTextBlockName.getText().toString();
        if (str.equals("")) {
            editTextBlockName.setError("Поле не должно быть пустым");
            return;
        }

        presenter.addBlock(str);
        editTextBlockName.setText("");
        presenter.sendNotification();
        changeData();
        adapter.notifyDataSetChanged();


    }

    @OnClick(R.id.text_view_dayoff)
    public void onDateClick() {
        dialogWithCalendar.show(getChildFragmentManager(), "CALENDAR");
    }

    @OnClick(R.id.button_save_room)
    public void saveRoomClick() {
        if (editTextRoomPrice.getText().toString().equals("")) {
            editTextRoomPrice.setError(getString(R.string.need_not_a_clear_field));
            return;
        }
        if (mRoomNumber.getText().toString().equals("")) {
            mRoomNumber.setError(getString(R.string.need_not_a_clear_field));
            return;
        }

        String date;
        date = dialogWithCalendar.getSelectedDate();
        if (date == null)
            date = "date";
        presenter.addRoom(switchWater.isChecked(), mRoomNumber.getText().toString(), editTextRoomPrice.getText().toString(),
                switchFree.isChecked(), (String) spinnerRoomBlockList.getSelectedItem(), date);
        presenter.sendNotification();
    }

    @OnClick(R.id.delete_all)
    public void deleteAllClick() {
        presenter.clearAll();
        presenter.sendNotification();
        changeData();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.button_sync_to_server)
    public void sendDataToServer() {
        presenter.createRequestForUpdate(mWaitDialog).execute();
    }

    @OnClick(R.id.button_sync_from_server)
    public void getDataFromServer() {
        presenter.getDataFromServer(mWaitDialog);
    }

    ArrayAdapter<String> adapter;
    List<String> list = new ArrayList<>();


    private void changeData() {
        list.clear();
        list.addAll(presenter.getBlockNameArray());
    }

    private void initViews(View view) {
        changeData();
        adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item,
                list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomBlockList.setAdapter(adapter);
    }


    @Override
    public Context getContextFromView() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setDate(String date) {

        mDayOffText.setText(date);
        Long dt = DialogWithCalendar.convertDate(date);
        if (dt != null)
            dialogWithCalendar.setDate(dt);
    }
}
