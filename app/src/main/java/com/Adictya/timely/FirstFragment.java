package com.Adictya.timely;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Adictya.timely.model.TimeSlots;
import com.Adictya.timely.model.TimeSlotsViewModel;
import com.Adictya.timely.ui.TimeSlotsAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {
    private static final String ARG_COUNT = "param1";
    private Integer counter;

    private TimeSlotsAdapter timeSlotsAdapter;
    private TimeSlotsViewModel timeSlotsViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        timeSlotsViewModel = new ViewModelProvider(this).get(TimeSlotsViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final TimeSlotsAdapter timeSlotsAdapter = new TimeSlotsAdapter(this.getContext());
        recyclerView.setAdapter(timeSlotsAdapter);

        timeSlotsViewModel.getAllTimeSlots().observe(getActivity(), new Observer<List<TimeSlots>>() {
            @Override
            public void onChanged(List<TimeSlots> timeSlots) {
                timeSlotsAdapter.setTimeSlots(timeSlots);
            }
        });
        return rootView;
    }
    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(Integer counter) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
