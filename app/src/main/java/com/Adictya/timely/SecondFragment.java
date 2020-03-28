package com.Adictya.timely;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.Adictya.timely.model.SlotsTableViewModelFactory;
import com.Adictya.timely.model.TimeSlots;
import com.Adictya.timely.model.TimeSlotsViewModel;
import com.Adictya.timely.ui.SlotsTableAdapter;
import com.Adictya.timely.ui.TimeSlotsAdapter;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondFragment extends Fragment {
    private static final String ARG_COUNT = "param1";
    private Boolean counter;
    private Integer slab;
    private ImageButton addButton;
    private TimeSlotsAdapter timeSlotsAdapter;
    private TimeSlotsViewModel timeSlotsViewModel;
    RecyclerView recyclerView;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private AutoCompleteTextView slotText;
    private AutoCompleteTextView courseText;
    private EditText classText;
    private static final String[] Brands = new String[] {
            "A11", "A2", "B1", "B2"};
    private static final String[] Labs = new String[] {
            "L31+L32", "L41+L42"
    };


    @Override
    public View onCreateView(
            final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        timeSlotsViewModel = new ViewModelProvider(this, new SlotsTableViewModelFactory(
                this.getActivity().getApplication(), counter)).get(TimeSlotsViewModel.class);

        final View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        addButton = rootView.findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog(rootView,inflater,timeSlotsViewModel);
            }
        });
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
    private void createPopupDialog(final View view, LayoutInflater inflaty, final TimeSlotsViewModel tsm) {
        builder = new AlertDialog.Builder(view.getContext());
        View vew = inflaty.inflate(R.layout.popup, null);
        slotText = vew.findViewById(R.id.slots_field);
        classText = vew.findViewById(R.id.class_field);
        courseText = vew.findViewById(R.id.courseCode_field);
        saveButton = vew.findViewById(R.id.popup_savebutton);

        if (counter) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                    android.R.layout.simple_dropdown_item_1line, Brands);
            slotText.setAdapter(adapter);
        }
        else  {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                    android.R.layout.simple_dropdown_item_1line, Labs);
            slotText.setAdapter(adapter);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Arrays.asList(Brands).contains(String.valueOf(slotText.getText())) || Arrays.asList(Labs).contains(String.valueOf(slotText.getText()))) {
                    TimeSlots timeSlots = new TimeSlots(String.valueOf(slotText.getText()), slab, String.valueOf(courseText.getText()), String.valueOf(classText.getText()));
                    tsm.insertTimeSlots(timeSlots);
                }else
                    Toast.makeText(view.getContext(),"Enter a Valid Slot",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(vew);
        dialog = builder.create();
        dialog.show();

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
            slab = getArguments().getInt(ARG_COUNT);
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
