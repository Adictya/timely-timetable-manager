package com.Adictya.timely;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class TimeTable_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_);

    }

    public void buttonClicked(View view){
        Intent myIntent = new Intent(TimeTable_Activity.this, MainActivity.class);
        switch (view.getId()){
            case R.id.timetable_button:
                TimeTable_Activity.this.startActivity(myIntent);
                break;
            case R.id.slottable_button:
                break;
        }
    }

}
