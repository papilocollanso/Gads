package com.example.gads;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mnumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fragment, int NumOfTabs) {
        super(fragment);
        this.mnumOfTabs = NumOfTabs;
    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                learning_leaders learning_leaders=new learning_leaders();
                return learning_leaders;

            case 1:
                skills_iq_leaders skills_iq_leaders= new skills_iq_leaders();
                return skills_iq_leaders;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mnumOfTabs;
    }
}
