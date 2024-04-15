package com.example.curierplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.curierplus.local.LocalStoreHelper;
import com.example.curierplus.ui.fragments.orders.CurrentOrdersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, new CurrentOrdersFragment(), "current_orders")
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}