package com.example.lyaho340hw1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Adding Tabs to Main screen
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText(R.string.tab_name_profile));
        tabs.addTab(tabs.newTab().setText(R.string.tab_name_matches));
        tabs.addTab(tabs.newTab().setText(R.string.tab_name_settings));

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ProfileFragment fragment = new ProfileFragment();
//        fragmentTransaction.add(R.id.main_fragment_container, fragment);
//        fragmentTransaction.commit();

        Log.d(TAG,"onCreate invoked");
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragment(), getResources().getString(R.string.tab_name_profile));
//        adapter.addFragment(new TileContentFragment(), "Tile");
//        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
}

}
