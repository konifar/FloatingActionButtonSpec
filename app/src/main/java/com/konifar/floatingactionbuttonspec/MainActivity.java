package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    private static final String SPEC_URL = "http://www.google.com/design/spec/components/buttons-floating-action-button.html#";

    @InjectView(R.id.list_view)
    ListView listView;

    private String[] titles = {
            "Selected state",
            "Animate in",
            "Tab swipe animation",
            "Transform to bar",
            "Fling out buttons"
    };

    public static void showWebPage(String url, Context context) {
        if (TextUtils.isEmpty(url)) return;

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_title, titles);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.list_view)
    public void onItemClickListView(int position) {
        String title = titles[position];
        switch (position) {
            case 0:
                SelectedStateActivity.start(this, title);
                break;
            case 1:
                AnimateInOutActivity.start(this, title);
                break;
            case 2:
                TabSwipeAnimationActivity.start(this, title);
                break;
            case 3:
                TransformToBarActivity.start(this, title);
                break;
            case 4:
                FlingOutButtonsActivity.start(this, title);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_link:
                showWebPage(SPEC_URL, this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
