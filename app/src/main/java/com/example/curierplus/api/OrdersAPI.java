package com.example.curierplus.api;

import android.util.Log;

import com.example.curierplus.enities.Order;
import com.example.curierplus.local.LocalStoreHelper;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrdersAPI {
    public static final String ORDER_URL = "http://192.168.157.75:3000/orders";
    public static final String SUCCESS_RES = "Заказы найдены";
    public static List<Order> loadOrdersForEmployee(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody body = null;
        body = new FormBody.Builder()
                .add("sessionKey", LocalStoreHelper.currentEmployee.sessionKey)
                .add("id", String.valueOf(LocalStoreHelper.currentEmployee.id))
                .add("status","Ожидание")
                .build();
        Request request = new Request.Builder()
                .url(ORDER_URL)
                .post(body)
                .header("Content-Type", "application/json")
                .build();
        try(Response res = client.newCall(request).execute()){
            JSONObject json = new JSONObject(res.body().string());
            //Log.d("RES", json.getJSONArray("orders").toString());
            return Order.parseOrdersArray(json.getJSONArray("orders"));
        }catch (Exception e){
            return null;
        }
    }
}
