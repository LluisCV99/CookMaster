package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceptesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReceptesViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}