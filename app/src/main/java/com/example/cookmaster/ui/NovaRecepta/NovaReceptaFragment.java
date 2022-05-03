package com.example.cookmaster.ui.NovaRecepta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.databinding.NovaReceptaBinding;

public class NovaReceptaFragment extends Fragment {

    private NovaReceptaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NovaReceptaViewModel novaReceptaViewModel =
                new ViewModelProvider(this).get(NovaReceptaViewModel.class);

        binding = NovaReceptaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //novaReceptaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void chose() {
    }
}