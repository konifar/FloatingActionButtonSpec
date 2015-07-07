package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.View;

import butterknife.InjectView;
import butterknife.OnClick;

public class AnimateInOutActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, AnimateInOutActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_animate_in_out;
    }

    @Override
    protected void initView() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        FabAnimationUtil.animateOut(fab);
    }

    @OnClick(R.id.root)
    void onClickRoot() {
        if (fab.getVisibility() == View.INVISIBLE) {
            FabAnimationUtil.animateIn(fab);
        }
    }

}
