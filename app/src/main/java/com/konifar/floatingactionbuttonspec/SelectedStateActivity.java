package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;

public class SelectedStateActivity extends BaseActivity {

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

}
