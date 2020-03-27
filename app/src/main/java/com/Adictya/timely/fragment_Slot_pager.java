package com.Adictya.timely;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Adictya.timely.ui.SlotPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class fragment_Slot_pager extends Fragment {
    private TabLayout tabLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_tab, container, false);
        ViewPager2 viewPager = rootView.findViewById(R.id.view_pager2);
        tabLayout = rootView.findViewById(R.id.tabs2);
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));
                    }
                }).attach();
        createCustomTabs(inflater,container);
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(0)).getCustomView()).setAlpha(1f);
        tabLayout.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getCustomView()).setAlpha(1f);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getCustomView()).setAlpha(0.54f);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getCustomView()).setAlpha(1f);
            }
        });
        return rootView;
    }
    private SlotPagerAdapter createCardAdapter() {
        return new SlotPagerAdapter(Objects.requireNonNull(getActivity()));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    private void createCustomTabs(LayoutInflater inflater, ViewGroup container){
        TextView tabOne = (TextView) inflater.inflate(R.layout.custom_tab_slot,container, false);
        tabOne.setText("Theory");
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(tabOne);

        TextView tabTwo = (TextView) inflater.inflate(R.layout.custom_tab_slot,container, false);
        tabTwo.setText("Lab");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(tabTwo);

    }
}
