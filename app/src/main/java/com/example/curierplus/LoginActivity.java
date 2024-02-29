package com.example.curierplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.curierplus.ui.fragments.auth.LoginFragment;
import com.example.curierplus.local.LocalStoreHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LocalStoreHelper.initSharedPreferences(getApplicationContext());
        LocalStoreHelper.employeeDataInit();
        if(LocalStoreHelper.currentEmployee == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment, new LoginFragment(), "login_fragment")
                    .commit();
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}