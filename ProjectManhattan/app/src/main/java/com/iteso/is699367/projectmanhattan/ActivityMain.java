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
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_place_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_white_24dp);

        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            Toast.makeText(this,
                    extras.getString(ActivityChoosing.ROLE), Toast.LENGTH_SHORT).show();
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fragmentManager){super(fragmentManager);}

        @Override
        public Fragment getItem(int i) {
            switch(i) {
                case 0:
                default:
                    Bundle extras = new Bundle();
                    String role = extras.getString(ActivityChoosing.ROLE);
                    if(role == "Drivey") {
                        return new FragmentDriveyHome();
                    }
                    else {
                        return new FragmentRideyHome();
                    }
                case 1:
                    return new FragmentAddresses();
                case 2:
                    return new FragmentProfile();
                case 3:
                    return new FragmentSettings();
            }
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
