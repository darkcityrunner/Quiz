package com.example.android.iqquiz2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaeldegroff on 3/20/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super (fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {

        return mFragmentList.size();
    }


//    int mNoOfTabs;
//
//    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
//        super (fm);
//        this.mNoOfTabs = NumberOfTabs;
//    }
//
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                LogicalQuestions tab1 = new LogicalQuestions();
//                return tab1;
//            case 1:
//                MathematicalQuestions tab2 = new MathematicalQuestions();
//                return tab2;
//            case 2:
//                VerbalQuestions tab3 = new VerbalQuestions();
//                return tab3;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return mNoOfTabs;
//    }
}
