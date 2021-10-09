package com.example.feelgoodmentalwellness.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.feelgoodmentalwellness.Fragment.CureFragment;
import com.example.feelgoodmentalwellness.Fragment.PostFragment;
import com.example.feelgoodmentalwellness.Fragment.ChatFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(@NonNull FragmentManager fm, Context myContext, int totalTabs) {
        super(fm);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PostFragment();
            case 1:
                return new CureFragment();
            case 2:
                return new ChatFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
