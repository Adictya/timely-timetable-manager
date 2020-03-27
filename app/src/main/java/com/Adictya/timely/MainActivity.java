package com.Adictya.timely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.paris.Paris;

public class MainActivity extends AppCompatActivity {
    private TextView slotText,timeText;
    private ImageView slotIcon,timeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fragment currentFragment = new FirstFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,currentFragment).commit();
        slotText = findViewById(R.id.slottable_title);
        slotIcon = findViewById(R.id.slottable_icon);
        timeText = findViewById(R.id.timetable_title);
        timeIcon = findViewById(R.id.timetable_icon);
    }

    public void buttonClicked(View view){
        Fragment currentFragment = null;
        switch (view.getId()){
            case R.id.timetable_button:
                Paris.style(slotText).apply(R.style.inactive_title);
                Paris.style(slotIcon).apply(R.style.inactive_item);
                Paris.style(timeText).apply(R.style.active_title);
                Paris.style(timeIcon).apply(R.style.active_item);
                currentFragment = new FirstFragment();
                break;
            case R.id.slottable_button:
                Paris.style(slotText).apply(R.style.active_title);
                Paris.style(slotIcon).apply(R.style.active_item);
                Paris.style(timeText).apply(R.style.inactive_title);
                Paris.style(timeIcon).apply(R.style.inactive_item);
                currentFragment = new SecondFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,currentFragment).commit();
    }
}