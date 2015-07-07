package com.konifar.floatingactionbuttonspec;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.list_view)
    ListView listView;

    private String[] titles = {
            "Selected state",
            "With counter",
            "Animate in",
            "Change view",
            "Tab swipe animation"
    };

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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

}
