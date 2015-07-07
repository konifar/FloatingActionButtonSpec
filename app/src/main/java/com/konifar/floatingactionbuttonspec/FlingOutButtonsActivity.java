package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;

import butterknife.InjectView;
import butterknife.OnClick;

public class FlingOutButtonsActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.fab_mini_5)
    FloatingActionButton fabMini5;
    @InjectView(R.id.fab_mini_4)
    FloatingActionButton fabMini4;
    @InjectView(R.id.fab_mini_3)
    FloatingActionButton fabMini3;
    @InjectView(R.id.fab_mini_2)
    FloatingActionButton fabMini2;
    @InjectView(R.id.fab_mini_1)
    FloatingActionButton fabMini1;

    private boolean fabSelected;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, FlingOutButtonsActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_fling_out_buttons;
    }

    @Override
    protected void initView() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);

        FabAnimationUtil.animateOut(fabMini1, 0L, null);
        FabAnimationUtil.animateOut(fabMini2, 0L, null);
        FabAnimationUtil.animateOut(fabMini3, 0L, null);
        FabAnimationUtil.animateOut(fabMini4, 0L, null);
        FabAnimationUtil.animateOut(fabMini5, 0L, null);
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        if (fabSelected) {
            FabAnimationUtil.rotateToUnSelect(fab);
            hideMiniButtons();
        } else {
            FabAnimationUtil.rotateToSelect(fab);
            showMiniButtons();
        }
        fab.setPressed(fabSelected);
        fab.jumpDrawablesToCurrentState();
        fabSelected = !fabSelected;
    }

    private void showMiniButtons() {
        FabAnimationUtil.animateInFast(fabMini1, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateInFast(fabMini2, null);
            }
        }, 20);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateInFast(fabMini3, null);
            }
        }, 40);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateInFast(fabMini4, null);
            }
        }, 60);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateInFast(fabMini5, null);
            }
        }, 80);
    }

    private void hideMiniButtons() {
        FabAnimationUtil.animateOutFast(fabMini5, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateOutFast(fabMini4, null);
            }
        }, 20);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateOutFast(fabMini3, null);
            }
        }, 40);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateOutFast(fabMini2, null);
            }
        }, 60);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FabAnimationUtil.animateOutFast(fabMini1, null);
            }
        }, 80);
    }
}
