package com.example.curierplus.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.curierplus.enities.Employee;
import com.example.curierplus.enities.WorkActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.Date;

public class LocalStoreHelper {
    public static final String LOCAL_USER = "local_user";
    public static final String SHARED_BASE = "local_base";
    public static final String LOCAL_ACTIVITY = "local_activity";
    public static SharedPreferences preferences;
    public static Employee currentEmployee;
    public static WorkActivity workActivity;
    public static void initSharedPreferences(Context context){
        if(preferences == null)
            preferences = context.getSharedPreferences(SHARED_BASE, Context.MODE_PRIVATE);
    }
    public static void employeeDataInit(){
        if(preferences == null) throw new NullPointerException();
        String employeeJSON = preferences.getString(LOCAL_USER, null);
        Gson gson = new Gson();
        currentEmployee = gson.fromJson(employeeJSON, Employee.class);
        workActivityInit();
    }
    public static void saveEmployeeToLocalBase(String employeeJSON){
        if(preferences == null) throw new NullPointerException();
        preferences.edit().putString(LOCAL_USER, employeeJSON).apply();
    }
    public static void removeEmployee(){
        if(preferences == null) throw new NullPointerException();
        currentEmployee = null;
        preferences.edit().remove(LOCAL_USER).apply();
    }
    public static void workActivityInit() {
        if(currentEmployee == null) return;
        String activityJSON = preferences.getString(LOCAL_ACTIVITY, null);
        if (activityJSON == null){
            workActivity = new WorkActivity(currentEmployee.getLastAuth(), 0);
            return;
        }
        Gson gson = new Gson();
        workActivity = gson.fromJson(activityJSON, WorkActivity.class);
        try {
            if (!DateHelper.isDateToday(workActivity.activityDate)) {
                workActivity.activityDate = currentEmployee.lastAuth;
                workActivity.ordersComplete = 0;
                return;
            }
        } catch (Exception e){
            workActivity = new WorkActivity(currentEmployee.getLastAuth(), 0);
            return;
        }
    }
    public static void saveWorkActivity(){
        Gson gson = new Gson();
        String workActivityJson = gson.toJson(workActivity);
        preferences.edit().putString(LOCAL_ACTIVITY, workActivityJson).apply();

    }
}
