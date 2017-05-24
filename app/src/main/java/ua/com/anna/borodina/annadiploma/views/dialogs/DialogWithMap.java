package ua.com.anna.borodina.annadiploma.views.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.ButterKnife;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.fragments.MapFragment;



public class DialogWithMap extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MapFragment dialog = new MapFragment();
        View v = inflater.inflate(R.layout.dialog_with_map, container, false);
        Button b = (Button) v.findViewById(R.id.button_dismiss);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        getChildFragmentManager().beginTransaction().add(R.id.container_for_map, dialog).commit();
        return v;
    }

}
