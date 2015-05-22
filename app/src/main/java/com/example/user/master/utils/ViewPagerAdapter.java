package com.example.user.master.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 27.04.2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private CharSequence[] titles = {"Formular Simplu", "Formular Avansat"};
    private int noOfTabs = 2;

    public ViewPagerAdapter(FragmentManager fmg){
           super(fmg);
    }

    @Override
    public Fragment getItem(int i) {
        return FormulareFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
