package com.example.partyup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class ScreenSlidePageFragment extends Fragment {

    private ViewPager2 mViewPager;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = view.findViewById(R.id.myPager);//Get ViewPager2 view
        mViewPager.setAdapter(new ViewPagerAdapter(getActivity()));//Attach the adapter with our ViewPagerAdapter passing the host activity
        mViewPager.setCurrentItem(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
    }
}