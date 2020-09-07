package com.example.gads;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class SectionsPageAdapter extends FragmentPagerAdapter {

    private Context Context;
    private String[] tabTitles= { "Learning Leaders ", "SKILL IQ Leaders" };

    public SectionsPageAdapter(@NonNull FragmentManager fm,Context context) {
        super(fm);
        this.Context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                    learning_leaders fragment1= new learning_leaders();
                    return fragment1;
            case 1:
                  skills_iq_leaders fragment2= new skills_iq_leaders();
                  return fragment2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  tabTitles[position];
    }
}
