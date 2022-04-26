package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceptaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReceptaViewModel() {
        mText = new MutableLiveData<>();

    }
    public LiveData<String> getText() {
        return mText;
    }
}