package com.Adictya.timely;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Adictya.timely.model.SlotsTableViewModelFactory;
import com.Adictya.timely.model.TimeSlots;
import com.Adictya.timely.model.TimeSlotsViewModel;
import com.Adictya.timely.model.TimeSlotsViewModelFactory;
import com.Adictya.timely.ui.SlotsTableAdapter;
import com.Adictya.timely.ui.TimeSlotsAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondFragment extends Fragment {
    private static final String ARG_COUNT = "param1";
    private Boolean counter;

    private TimeSlotsAdapter timeSlotsAdapter;
    private TimeSlotsViewModel timeSlotsViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        timeSlotsViewModel = new ViewModelProvider(this, new SlotsTableViewModelFactory(
                this.getActivity().getApplication(), counter)).get(TimeSlotsViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final SlotsTableAdapter timeSlotsAdapter = new SlotsTableAdapter(this.getContext());
        recyclerView.setAdapter(timeSlotsAdapter);

        timeSlotsViewModel.getAllSlots().observe(getActivity(), new Observer<List<TimeSlots>>() {
            @Override
            public void onChanged(List<TimeSlots> timeSlots) {
                timeSlotsAdapter.setTimeSlots(timeSlots);
            }
        });
        return rootView;
    }
    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(Integer counter) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT,counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getInt(ARG_COUNT) == 1)
                counter = true;
            else
                counter = false;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
