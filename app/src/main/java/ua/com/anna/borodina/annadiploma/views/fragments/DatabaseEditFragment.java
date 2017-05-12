package ua.com.anna.borodina.annadiploma.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.firebase.messaging.FirebaseMessaging;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.presenters.DatabaseEditPresenterImpl;
import ua.com.anna.borodina.annadiploma.presenters.interfaces.DatabaseEditPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IDatabaseEdit;

/**
 * Created by Андрей on 01.05.2017.
 */

public class DatabaseEditFragment extends BaseFragment implements IDatabaseEdit {


  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.switch_water)
  Switch switchWater;
  @BindView(R.id.switch_free)
  Switch switchFree;
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
  LinearLayout activityDatabaseEdit;
  Unbinder unbinder;
  private DatabaseEditPresenter presenter;

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
    } else {
      presenter.addBlock(str);
      editTextBlockName.setText("");
      presenter.sendNotification();

    }
  }

  @OnClick(R.id.button_save_room)
  public void saveRoomClick() {
    if (editTextRoomPrice.getText().toString().equals("")) {
      editTextRoomPrice.setError("Поле не должно быть пустым");
    } else {
      presenter.sendNotification();
      presenter.addRoom(switchWater.isChecked(), "1", editTextRoomPrice.getText().toString(),
          switchFree.isChecked(), (String) spinnerRoomBlockList.getSelectedItem());
    }
  }

  @OnClick(R.id.delete_all)
  public void deleteAllClick() {
    presenter.clearAll();
    presenter.sendNotification();
  }

  ArrayAdapter<String> adapter;

  private void initViews(View view) {
    adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item,
        presenter.getBlockNameArray());
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
}
