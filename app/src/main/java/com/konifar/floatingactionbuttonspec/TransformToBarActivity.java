package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.InjectView;
import butterknife.OnClick;

public class TransformToBarActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.toolbar_footer)
    Toolbar toolbarFooter;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, TransformToBarActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_transform_to_bar;
    }

    @Override
    protected void initView() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        if (fab.getVisibility() == View.VISIBLE) {
            FabAnimationUtil.transformIn(fab, toolbarFooter);
        }
    }

    @OnClick(R.id.root)
    void onClickRoot() {
        if (fab.getVisibility() == View.INVISIBLE) {
            FabAnimationUtil.transformOut(fab, toolbarFooter);
        }
    }

}
