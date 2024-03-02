package com.example.curierplus.ui.fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curierplus.R;
import com.example.curierplus.databinding.FragmentProfileBinding;
import com.example.curierplus.local.LocalStoreHelper;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.fullName.setText(LocalStoreHelper.currentEmployee.getFullName());
        binding.workStatus.setText(String.format(getString(R.string.working_status),"Работает"));
        binding.totalOrdersToday.setText(String.format(getString(R.string.total_orders_today),15));
        binding.lastAuthDate.setText(String.format(getString(R.string.last_auth_date), "05.02.2024"));
    }
}