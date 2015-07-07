package com.konifar.floatingactionbuttonspec;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TabSwipeAnimationFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";

    @InjectView(R.id.txt_title)
    TextView txtTitle;

    public static Fragment create(String title) {
        TabSwipeAnimationFragment fragment = new TabSwipeAnimationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_swipe_animation, container, false);
        ButterKnife.inject(this, view);

        if (getArguments() != null) {
            txtTitle.setText(getArguments().getString(ARG_TITLE));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
