package ua.com.anna.borodina.annadiploma.presenters.interfaces;

import ua.com.anna.borodina.annadiploma.views.interfaces.BaseView;


public interface BasePresenter<T extends BaseView> {

    public void onAttachView(T view);

    public void onDetachView();


}
