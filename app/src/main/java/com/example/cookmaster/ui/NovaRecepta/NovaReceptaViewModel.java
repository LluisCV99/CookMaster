package com.example.cookmaster.ui.NovaRecepta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NovaReceptaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NovaReceptaViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}