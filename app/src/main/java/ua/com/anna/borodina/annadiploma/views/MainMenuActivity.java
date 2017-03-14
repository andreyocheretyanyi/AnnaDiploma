package ua.com.anna.borodina.annadiploma.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    private void startAnimatinOnView(final View view, int animationRes){
        Animation animation = AnimationUtils.loadAnimation(this, animationRes);
        animation.setDuration(1500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) { }

        });

        view.startAnimation(animation);
    }


    private void initView(){
        View v = findViewById(R.id.devide_lne);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        startAnimatinOnView(map,R.anim.animation_from_left_bottom_corner);
        startAnimatinOnView(selectBlocks,R.anim.animation_from_right_bottom_corner);
        startAnimatinOnView(aboutAuthor,R.anim.animation_from_left_bottom_corner);
        startAnimatinOnView(editData,R.anim.animation_from_right_bottom_corner);
        startAnimatinOnView(exit,R.anim.animation_from_left_bottom_corner);
        startAnimatinOnView(v,R.anim.animation_fade_in);
        startAnimatinOnView(toolbar,R.anim.animation_fade_in);
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
