package com.example.user.master.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;

import java.util.HashMap;

/**
 * Created by User on 29.04.2015.
 */
public class ViewPagerLinesAdapter extends FragmentPagerAdapter {

    private CharSequence[] titles = {"Tramvaie", "Troleibuze", "Autobuze"};
    private int noOfTabs = 3;
    HashMap<String, HashMap<String, String>> liniiSiStatii = null;
    static DisertatieDatabaseHelper helper = null;

    public ViewPagerLinesAdapter(FragmentManager fmg, Context c){

        super(fmg);

        helper = new DisertatieDatabaseHelper(c);
        liniiSiStatii  = helper.getStatiiAndLinii();
    }
    @Override
    public Fragment getItem(int i) {
        return LiniiFragment.newInstance(i, liniiSiStatii);
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
