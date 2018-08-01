package com.example.alayesanmifemi.mlforensic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Alayesanmi Femi on 28/06/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {


    int numberOfTabs;

    public PagerAdapter(FragmentManager fm,int NumberofTabs){
        super(fm);
        this.numberOfTabs = NumberofTabs;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                CreateCase_1 tab1 = new CreateCase_1();
                return tab1;
            case 1:
                CreateCase_2 tab2 = new CreateCase_2();
                return tab2;
//            case 2:
//                CreateCase_3 tab3 = new CreateCase_3();
//                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
