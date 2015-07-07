package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SelectedStateActivity extends BaseActivity {

    @InjectView(R.id.root)
    RelativeLayout root;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

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
        //
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        FabAnimationUtil.animateOut(fab);
    }

    @OnClick(R.id.root)
    void onClickRoot() {
        if (fab.getVisibility() == View.GONE) {
            FabAnimationUtil.animateIn(fab);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
