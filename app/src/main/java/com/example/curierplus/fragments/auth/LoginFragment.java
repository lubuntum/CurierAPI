package com.example.curierplus.fragments.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.curierplus.R;
import com.example.curierplus.api.UserAuth;
import com.example.curierplus.databinding.FragmentLoginBinding;
import com.example.curierplus.fragments.orders.CurrentOrdersFragment;
import com.example.curierplus.local.LocalStoreHelper;

import java.io.IOException;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private final MutableLiveData<String> status = new MutableLiveData<>();
    private final Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        submitBtnInit();
        statusObserverInit();
    }
    private void submitBtnInit(){
        binding.submitBtn.setOnClickListener(view -> {
            if(!filterData()) return;
            binding.progress.setVisibility(View.VISIBLE);
            Runnable submitRnb = () -> {
                String login = binding.login.getText().toString();
                String pass = binding.pass.getText().toString();
                try {
                    status.postValue(UserAuth.authWithLoginAndPass(login, pass));
                } catch (IOException e) {
                    status.postValue(UserAuth.NETWORK_ERROR);
                }
            };
            new Thread(submitRnb).start();
        });
    }

    private void statusObserverInit(){
        status.observe(getViewLifecycleOwner(), status -> {
            binding.progress.setVisibility(View.GONE);
            if(UserAuth.SUC_STATUS.matches(status)){
                LocalStoreHelper.employeeDataInit();
                openOrdersFragment();
            }
            Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
        });
    }

    private void openOrdersFragment(){
        getParentFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, new CurrentOrdersFragment(), "current_orders")
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }

    private boolean filterData(){
        if (binding.login.getText().toString().matches(" *")){
            binding.login.setError("Введите логин");
            return false;
        }
        if (binding.pass.getText().toString().matches(" *")){
            binding.pass.setError("Введите пароль");
            return false;
        }
        return true;
    }
}