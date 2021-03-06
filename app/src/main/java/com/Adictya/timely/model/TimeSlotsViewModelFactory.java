package com.Adictya.timely.model;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TimeSlotsViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private Integer day;
    private Boolean bool;


    public TimeSlotsViewModelFactory(Application application, Integer param) {
        mApplication = application;
        day = param;
    }


    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new TimeSlotsViewModel(mApplication, day);
    }

}
