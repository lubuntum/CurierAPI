package com.example.curierplus.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.curierplus.enities.Employee;
import com.google.gson.Gson;

public class LocalStoreHelper {
    public static final String LOCAL_USER = "local_user";
    public static final String SHARED_BASE = "local_base";
    public static SharedPreferences preferences;
    public static Employee currentEmployee;
    public static void initSharedPreferences(Context context){
        if(preferences == null)
            preferences = context.getSharedPreferences(SHARED_BASE, Context.MODE_PRIVATE);
    }
    public static void employeeDataInit(){
        if(preferences == null) throw new NullPointerException();
        String employeeJSON = preferences.getString(LOCAL_USER, null);
        Gson gson = new Gson();
        currentEmployee = gson.fromJson(employeeJSON, Employee.class);
    }
    public static void saveEmployeeToLocalBase(String employeeJSON){
        if(preferences == null) throw new NullPointerException();
        preferences.edit().putString(LOCAL_USER, employeeJSON).apply();
    }
    public static void removeEmployee(){
        if(preferences == null) throw new NullPointerException();
        preferences.edit().remove(LOCAL_USER).apply();
    }
}
