package ua.com.anna.borodina.annadiploma.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.ButterKnife.Setter;
import butterknife.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.List;
import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.views.fragments.BaseFragment;
import ua.com.anna.borodina.annadiploma.views.fragments.BlockListFragment;
import ua.com.anna.borodina.annadiploma.views.fragments.DatabaseEditFragment;
import ua.com.anna.borodina.annadiploma.views.fragments.MapFragment;

public class AppActivity extends AppCompatActivity {

  @BindView(R.id.frame_fragment_container)
  FrameLayout frameFragmentContainer;
  @BindView(R.id.main_menu_root)
  LinearLayout mainMenuRoot;
  @BindView(R.id.button_open_menu)
  ImageButton buttonOpenMenu;
  @BindView(R.id.item_menu_1)
  LinearLayout menuItemOne;
  @BindView(R.id.item_menu_2)
  LinearLayout menuItemTwo;
  @BindView(R.id.item_menu_3)
  LinearLayout menuItemThree;
  @BindView(R.id.item_menu_4)
  LinearLayout menuItemFour;
  @BindView(R.id.item_menu_5)
  LinearLayout menuItemFive;
  @BindView(R.id.toolbarInMain)
  Toolbar mToolbar;
  @BindViews({R.id.main_menu_root, R.id.item_menu_1, R.id.item_menu_2, R.id.item_menu_3,
      R.id.item_menu_4, R.id.item_menu_5})
  List<View> views;

  private static int DURATION = 300;
  private static boolean isShow = false;

  private FragmentManager mFragmentManager;
  private BaseFragment mBlocksListFragment, mDataBaseEditFragment, mMapFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_app);
    ButterKnife.bind(this);
    mToolbar.setPadding(0,getStatusBarHeight(),0,0);
    mToolbar.getLayoutParams().height +=getStatusBarHeight()/2;
    makeTint();
    mFragmentManager = getSupportFragmentManager();
    mBlocksListFragment = new BlockListFragment();
    mDataBaseEditFragment = new DatabaseEditFragment();
    mMapFragment = new MapFragment();
    setFragmentToContainer(mMapFragment);
  }

  public int getStatusBarHeight() {
    int result = 0;
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }

  private void makeTint() {
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    // enable status bar tint
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setTintColor(Color.parseColor("#00000000"));
  }

  @OnClick(R.id.button_open_menu)
  public void onOpenMenuClicked() {
    showMenu();
  }


  @OnClick({R.id.item_menu_1, R.id.item_menu_2, R.id.item_menu_3, R.id.item_menu_4,
      R.id.item_menu_5})
  public void onClickedInMenu(View view) {
    switch (view.getId()) {
      case R.id.item_menu_1:
        setFragmentToContainer(mMapFragment);
        hideMenu();
        break;
      case R.id.item_menu_2:
        setFragmentToContainer(mBlocksListFragment);
        hideMenu();
        break;
      case R.id.item_menu_3:
        setFragmentToContainer(mDataBaseEditFragment);
        hideMenu();
        break;
      case R.id.item_menu_4:
        hideMenu();
        break;
      case R.id.item_menu_5:
        hideMenu();
        break;
    }
  }


  private void setFragmentToContainer(BaseFragment fragment) {
    mFragmentManager.beginTransaction().replace(R.id.frame_fragment_container, fragment).commit();
  }

  // start block which responsible for work with animations
  private ObjectAnimator createOAFromShow(View view) {
    ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -2000f, 0f);
    animation.addListener(AnimationListenerForItems(view));
    animation.setDuration(DURATION);
    return animation;
  }


  private void showMenu() {
    if (!isShow) {
      isShow = true;
      showMenuAnimator().start();
    }
  }

  private void hideMenu() {
    if (isShow) {
      isShow = false;
      hideMenuAnimator().start();
    }
  }

  private AnimatorSet showMenuAnimator() {
    AnimatorSet set = new AnimatorSet();

    set.playSequentially(
        createOAFromShow(mainMenuRoot),
        createOAFromShow(menuItemFour),
        createOAFromShow(menuItemThree),
        createOAFromShow(menuItemTwo),
        createOAFromShow(menuItemOne)
    );
    set.addListener(AnimationListenerForSet());
    return set;
  }

  private AnimatorSet hideMenuAnimator() {

    AnimatorSet set = new AnimatorSet();
    set.playSequentially(
        ObjectAnimator.ofFloat(mainMenuRoot, View.TRANSLATION_Y, 0f, -2000f)
    );
    set.setDuration(DURATION);
    set.addListener(AnimationListenerForSet());
    return set;
  }

  private AnimatorListenerAdapter AnimationListenerForItems(final View view) {
    return new AnimatorListenerAdapter() {
      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        view.setVisibility(View.VISIBLE);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
      }
    };
  }

  private AnimatorListenerAdapter AnimationListenerForSet() {
    return new AnimatorListenerAdapter() {
      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        if (isShow) {
          ButterKnife.apply(views, CLICKABLE, true);
        } else {
          ButterKnife.apply(views, CLICKABLE, false);
          hideItems();
        }
      }
    };
  }

  private void hideItems() {
    for (View view : views) {
      if (view.getId() == mainMenuRoot.getId()) {
        view.setVisibility(View.GONE);
      } else if (view.getId() != mainMenuRoot.getId() && view.getId() != menuItemFive.getId()) {
        view.setVisibility(View.INVISIBLE);
      }
    }
  }

  static final Setter<View, Boolean> CLICKABLE = new Setter<View, Boolean>() {
    @Override
    public void set(View view, Boolean value, int index) {
      view.setClickable(value);
    }
  };
  // end block with animations

}
