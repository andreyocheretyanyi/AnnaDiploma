package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import ua.com.anna.borodina.annadiploma.views.interfaces.BaseView;

/**
 * Created by admin on 24.02.2017.
 */

public interface BasePresenter<T extends BaseView> {

    public void onAttachView(T view);

    public void onDetachView();



}
