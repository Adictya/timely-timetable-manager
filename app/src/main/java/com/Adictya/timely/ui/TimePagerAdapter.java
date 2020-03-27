package com.Adictya.timely.ui;

import com.Adictya.timely.FirstFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TimePagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 7;
    public TimePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        return FirstFragment.newInstance(position);
    }
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}
