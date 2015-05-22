package com.example.user.master.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 29.04.2015.
 */
public class ViewPagerLinesAdapter extends FragmentPagerAdapter {

    private CharSequence[] titles = {"Tramvaie", "Troleibuze", "Autobuze"};
    private int noOfTabs = 3;

    public ViewPagerLinesAdapter(FragmentManager fmg){

        super(fmg);
    }
    @Override
    public Fragment getItem(int i) {
        return LiniiFragment.newInstance(i);
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
