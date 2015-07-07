package com.konifar.floatingactionbuttonspec;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class TabSwipeAnimationActivity extends BaseActivity {

    private final String[] tabTitles = {"Star", "Thumb", "Person"};

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, TabSwipeAnimationActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_tab_swipe_animation;
    }

    @Override
    protected void initView() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.setDisplayHomeAsUpEnabled(true);

        Adapter adapter = new Adapter(getSupportFragmentManager());
        for (String title : tabTitles) {
            adapter.addFragment(TabSwipeAnimationFragment.create(title), title);
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                FabAnimationUtil.animateOut(fab, new FabAnimationUtil.AnimateCallback() {
                    @Override
                    public void onAnimationStart() {
                    }

                    @Override
                    public void onAnimationEnd() {
                        switchFab(position);
                        FabAnimationUtil.animateIn(fab);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void switchFab(int position) {
        int colorResId = R.color.accent500;
        int drawableResId = R.drawable.ic_star_white_24dp;
        switch (position) {
            case 0:
                colorResId = R.color.accent500;
                drawableResId = R.drawable.ic_star_white_24dp;
                break;
            case 1:
                colorResId = R.color.teal500;
                drawableResId = R.drawable.ic_thumb_up_white_24dp;
                break;
            case 2:
                colorResId = R.color.deep_orange500;
                drawableResId = R.drawable.ic_person_add_white_24dp;
                break;
        }
        fab.setRippleColor(getResources().getColor(colorResId));
        fab.setImageResource(drawableResId);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}
