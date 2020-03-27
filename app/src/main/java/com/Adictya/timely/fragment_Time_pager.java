package com.Adictya.timely;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Adictya.timely.model.TimeSlots;
import com.Adictya.timely.model.TimeSlotsViewModel;
import com.Adictya.timely.ui.TimeSlotsAdapter;
import com.Adictya.timely.ui.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class fragment_Time_pager extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.first_tab, container, false);
        viewPager = rootView.findViewById(R.id.view_pager);
        tabLayout = rootView.findViewById(R.id.tabs);

        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));
                    }
                }).attach();
        createCustomTabs(inflater,container);
        tabLayout.getTabAt(0).getCustomView().setAlpha(1f);
        tabLayout.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    tab.getCustomView().setAlpha(1f);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(0.54f);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(1f);
            }
        });
        return rootView;
    }
    private ViewPagerAdapter createCardAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        return adapter;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    private void createCustomTabs(LayoutInflater inflater, ViewGroup container){
        LinearLayout tabOne = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        TextView date = tabOne.findViewById(R.id.tab_date);
        TextView day = tabOne.findViewById(R.id.tab_day);
        Dater(date,day,0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabTwo = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabTwo.findViewById(R.id.tab_date);
        day = tabTwo.findViewById(R.id.tab_day);
        Dater(date,day,1);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabThree.findViewById(R.id.tab_date);
        day = tabThree.findViewById(R.id.tab_day);
        Dater(date,day,2);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        LinearLayout tabFour = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabFour.findViewById(R.id.tab_date);
        day = tabFour.findViewById(R.id.tab_day);
        Dater(date,day,3);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        LinearLayout tabFive = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabFive.findViewById(R.id.tab_date);
        day = tabFive.findViewById(R.id.tab_day);
        Dater(date,day,4);
        tabLayout.getTabAt(4).setCustomView(tabFive);

        LinearLayout tabSix = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabSix.findViewById(R.id.tab_date);
        day = tabSix.findViewById(R.id.tab_day);
        Dater(date,day,5);
        tabLayout.getTabAt(5).setCustomView(tabSix);

        LinearLayout tabSeven = (LinearLayout) inflater.inflate(R.layout.custom_tab,container, false);
        date = tabSeven.findViewById(R.id.tab_date);
        day = tabSeven.findViewById(R.id.tab_day);
        Dater(date,day,6);
        tabLayout.getTabAt(6).setCustomView(tabSeven);
    }

    private void Dater(TextView date,TextView day,Integer index){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.UK);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM", Locale.UK);
        Integer sday;
        cal.add(Calendar.DATE,index);
        String sdate = dateFormat.format(cal.getTime());
        String[] suffixes =
                //    0     1     2     3     4     5     6     7     8     9
                { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    10    11    12    13    14    15    16    17    18    19
                        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                        //    20    21    22    23    24    25    26    27    28    29
                        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    30    31
                        "th", "st" };
        sdate = sdate.replaceAll("0*","")+suffixes[cal.get(Calendar.DAY_OF_MONTH)]+" "+dateFormat2.format(cal.getTime());
        date.setText(sdate);
        String[] daysoftheWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        sday = cal.get(Calendar.DAY_OF_WEEK)-1;
        day.setText(daysoftheWeek[sday]);
    }
}


