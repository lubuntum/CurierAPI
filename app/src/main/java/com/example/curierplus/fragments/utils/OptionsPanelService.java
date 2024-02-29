package com.example.curierplus.fragments.utils;

import android.animation.Animator;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.curierplus.R;
import com.example.curierplus.databinding.OptionsPanelBinding;
import com.example.curierplus.fragments.auth.LoginFragment;
import com.example.curierplus.fragments.orders.CurrentOrdersFragment;
import com.example.curierplus.fragments.profile.ProfileFragment;
import com.example.curierplus.local.LocalStoreHelper;

public class OptionsPanelService {
    OptionsPanelBinding binding;
    FragmentManager fragmentManager;
    private int panelState = View.INVISIBLE;
    public OptionsPanelService(OptionsPanelBinding binding,FragmentManager fg){
        this.binding = binding;
        this.fragmentManager = fg;
    }
    public void initOptions(){
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
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, new LoginFragment(), "login")
                    .commit();
        });
    }
    public void ordersBtnInit(){
        binding.orders.setOnClickListener(v->{
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, new CurrentOrdersFragment(),"orders")
                    .commit();
        });
    }
    public void profileBtnInit(){
        binding.profile.setOnClickListener(v->{
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, new ProfileFragment(), "profile")
                    .commit();
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
