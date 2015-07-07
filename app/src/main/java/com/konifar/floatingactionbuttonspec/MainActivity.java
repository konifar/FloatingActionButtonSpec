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
            "Animate in",
            "Tab swipe animation",
            "Transform to bar"
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
                AnimateInOutActivity.start(this, title);
                break;
            case 2:
                TabSwipeAnimationActivity.start(this, title);
                break;
            case 3:
                TransformToBarActivity.start(this, title);
                break;
        }
    }

}
