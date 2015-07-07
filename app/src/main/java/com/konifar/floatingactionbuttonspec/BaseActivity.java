package com.konifar.floatingactionbuttonspec;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public abstract class BaseActivity extends AppCompatActivity {

    static final String EXTRA_TITLE = "extra_title";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        ActionBar bar = getSupportActionBar();
        if (title != null && bar != null) bar.setTitle(title);

        initView();
    }

    abstract int getLayoutResId();

    protected abstract void initView();

}
