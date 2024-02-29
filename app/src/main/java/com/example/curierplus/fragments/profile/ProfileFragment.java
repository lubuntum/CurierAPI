package com.example.curierplus.fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curierplus.R;
import com.example.curierplus.databinding.FragmentProfileBinding;
import com.example.curierplus.fragments.utils.OptionsPanelService;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    OptionsPanelService panelService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        panelService = new OptionsPanelService(binding.panel, getParentFragmentManager());
        panelService.initOptions();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}