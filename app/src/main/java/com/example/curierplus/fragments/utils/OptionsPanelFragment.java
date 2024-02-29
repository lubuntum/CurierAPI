package com.example.curierplus.fragments.utils;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.curierplus.LoginActivity;
import com.example.curierplus.R;
import com.example.curierplus.databinding.OptionsPanelBinding;
import com.example.curierplus.fragments.auth.LoginFragment;
import com.example.curierplus.fragments.orders.CurrentOrdersFragment;
import com.example.curierplus.fragments.profile.ProfileFragment;
import com.example.curierplus.local.LocalStoreHelper;

public class OptionsPanelFragment extends Fragment {
    OptionsPanelBinding binding;
    private int panelState = View.INVISIBLE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = OptionsPanelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showBtnInit();
        logoutBtnInit();
        ordersBtnInit();
        profileBtnInit();
    }
    public void showBtnInit(){
        binding.options.setOnClickListener(v->{
            animRotate(binding.options);
        });
    }

    public void logoutBtnInit(){
        binding.logout.setOnClickListener(view -> {
            LocalStoreHelper.removeEmployee();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
    public void ordersBtnInit(){
        binding.orders.setOnClickListener(v->{
            binding.orders.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6ea8fe")));
            binding.profile.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C8BFE7")));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, new CurrentOrdersFragment(),"orders")
                    .commit();
            animRotate(binding.options);
        });
    }
    public void profileBtnInit(){

        binding.profile.setOnClickListener(v->{
            binding.orders.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C8BFE7")));
            binding.profile.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6ea8fe")));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, new ProfileFragment(), "profile")
                    .commit();
            animRotate(binding.options);
        });
    }

    private void animRotate(View view){
        view.setEnabled(false);
        view.animate().rotationBy(180).setDuration(250).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                view.setEnabled(true);
                if (panelState == View.VISIBLE) panelState = View.INVISIBLE;
                else panelState = View.VISIBLE;
                displayPanel(panelState);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        }).start();
    }
    private void displayPanel(int visibility){
        binding.logout.setVisibility(visibility);
        binding.orders.setVisibility(visibility);
        binding.profile.setVisibility(visibility);
    }


}
