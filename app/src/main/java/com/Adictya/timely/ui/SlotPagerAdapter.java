package com.Adictya.timely.ui;

import com.Adictya.timely.SecondFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SlotPagerAdapter extends FragmentStateAdapter {

    private static final int CARD_ITEM_SIZE = 2;
    public SlotPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        return SecondFragment.newInstance(position);
    }
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }

}
