package com.Adictya.timely.model;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SlotsTableViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private Boolean bool;


    public SlotsTableViewModelFactory(Application application, Boolean param) {
        mApplication = application;
        bool = param;
    }


    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new TimeSlotsViewModel(mApplication, bool);
    }
}
