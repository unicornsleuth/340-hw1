package com.example.lyaho340hw1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] tabNames;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabNames = new String[]{getString(R.string.tab_name_profile),
                getString(R.string.tab_name_matches),
                getString(R.string.tab_name_settings)};

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        ViewPager2 viewPager = (ViewPager2) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        new TabLayoutMediator(tabs, viewPager, (tab, position) -> tab.setText(tabNames[position])
        ).attach();

        // loadSignInState();

        Log.d(TAG,"onCreate invoked");
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager2 viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(new ProfileFragment(), getResources().getString(R.string.tab_name_profile));
        adapter.addFragment(new MatchesFragment(), getResources().getString(R.string.tab_name_matches));
        adapter.addFragment(new SettingsFragment(), getResources().getString(R.string.tab_name_settings));
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager, Lifecycle lifecycle) {
            super(manager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0:
                    return new ProfileFragment();
                case 1:
                    return new MatchesFragment();
                case 2:
                    return new SettingsFragment();
                default:
                    return null;
            }

        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }
    }

}
