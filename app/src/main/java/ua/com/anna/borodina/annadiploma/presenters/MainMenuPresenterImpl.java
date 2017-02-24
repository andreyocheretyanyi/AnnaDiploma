package ua.com.anna.borodina.annadiploma.presenters;

import android.content.Context;

import ua.com.anna.borodina.annadiploma.presenters.interfaces.MainMenuPresenter;
import ua.com.anna.borodina.annadiploma.views.interfaces.IMainMenuView;

/**
 * Created by admin on 24.02.2017.
 */

public class MainMenuPresenterImpl implements MainMenuPresenter {

    private IMainMenuView mMainMenuView;
    private Context context;

    @Override
    public void onAttachView(IMainMenuView view) {
        mMainMenuView = view;
        context = mMainMenuView.getContext();

    }

    @Override
    public void onDetachView() {

    }
}
