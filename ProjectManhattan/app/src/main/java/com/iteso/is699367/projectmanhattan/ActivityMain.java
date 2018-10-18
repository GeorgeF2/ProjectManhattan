package com.iteso.is699367.projectmanhattan;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Locale;


public class ActivityMain extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    SectionsPagerAdapter sectionPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.activity_main_view_pager);
        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout = findViewById(R.id.activity_main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_place_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_black_24dp);

        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            Toast.makeText(this,
                    extras.getString(ActivityChoosing.ROLE), Toast.LENGTH_SHORT).show();
        }
    }

    public static class PlaceholderFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    Bundle extras =  getActivity().getIntent().getExtras();
                    if (extras != null) {
                        if (extras.getString(ActivityChoosing.ROLE) == "Drivey") {
                            rootView = inflater.inflate(R.layout.fragment_home,
                                    container, false);
                        } else {
                            rootView = inflater.inflate(R.layout.fragment_home,
                                    container, false);
                        }
                        return rootView;
                    }
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_addresses,
                            container, false);
                    return rootView;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_profile,
                            container, false);
                    return rootView;
                default:
                    rootView = inflater.inflate(R.layout.fragment_settings,
                            container, false);
                    return rootView;
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fragmentManager){super(fragmentManager);}

        @Override
        public Fragment getItem(int i) {
            return PlaceholderFragment.newInstance(i + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


    }
}
