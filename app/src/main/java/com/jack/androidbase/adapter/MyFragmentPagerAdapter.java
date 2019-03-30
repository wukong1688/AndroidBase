package com.jack.androidbase.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jack.androidbase.activity.UIFragment2Activity;
import com.jack.androidbase.fragment.MyFragment1;
import com.jack.androidbase.fragment.MyFragment2;
import com.jack.androidbase.fragment.MyFragment3;
import com.jack.androidbase.fragment.MyFragment4;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private MyFragment1 myFragment1 = null;
    private MyFragment2 myFragment2 = null;
    private MyFragment3 myFragment3 = null;
    private MyFragment4 myFragment4 = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();
        myFragment4 = new MyFragment4();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case UIFragment2Activity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case UIFragment2Activity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case UIFragment2Activity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case UIFragment2Activity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

}