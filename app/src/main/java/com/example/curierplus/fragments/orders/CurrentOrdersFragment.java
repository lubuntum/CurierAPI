package com.example.curierplus.fragments.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curierplus.R;
import com.example.curierplus.databinding.FragmentCurrentOrdersBinding;
import com.example.curierplus.databinding.OptionsPanelBinding;
import com.example.curierplus.fragments.auth.LoginFragment;
import com.example.curierplus.local.LocalStoreHelper;

/*
* Сделать панель выезжающей + реализовать заказы и профиль работника
* и готово.
* */
public class CurrentOrdersFragment extends Fragment {

    FragmentCurrentOrdersBinding binding;
    public CurrentOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentOrdersBinding.inflate(inflater, container, false);
        //panelBinding = binding.panel;
        //optionsPanelService = new OptionsPanelService(binding.panel, getParentFragmentManager());
        //optionsPanelService.initOptions();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}