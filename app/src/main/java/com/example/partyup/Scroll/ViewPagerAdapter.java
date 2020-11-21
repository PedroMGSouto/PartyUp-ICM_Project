package com.example.partyup.Scroll;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.partyup.Features.Frag_GameFilter;
import com.example.partyup.Requests.Frag_NewRequest;
import com.example.partyup.Requests.Frag_MainScreen_Requests;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] mFragments = new Fragment[] {//Initialize fragments views
            new Frag_GameFilter(),
            new Frag_MainScreen_Requests(),
            new Frag_NewRequest()

    };

    public ViewPagerAdapter(FragmentActivity fa){//Pager constructor receives Activity instance
        super(fa);
    }

    @Override
    public int getItemCount() {
        return mFragments.length;//Number of fragments displayed
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }
}