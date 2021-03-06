package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;

import butterknife.InjectView;
import butterknife.OnClick;

public class SelectedStateActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private boolean fabSelected;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, SelectedStateActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_selected_state;
    }

    @Override
    protected void initView() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        if (fabSelected) {
            FabAnimationUtil.rotateToUnSelect(fab);
        } else {
            FabAnimationUtil.rotateToSelect(fab);
        }
        fab.setPressed(fabSelected);
        fab.jumpDrawablesToCurrentState();
        fabSelected = !fabSelected;
    }

}
