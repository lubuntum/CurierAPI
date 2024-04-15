package com.example.curierplus.api;

import com.example.curierplus.enities.Food;
import com.example.curierplus.enities.Order;
import com.example.curierplus.local.LocalStoreHelper;

import org.json.JSONObject;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrdersAPI {
    public static final String URL = "http://192.168.50.75:3000";
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
                .url(String.format("%s/%s",URL, "orders"))
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
    public static List<Food> loadFoodByOrder(Order order){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(String.format("%s/%s",URL, "orders/food")).newBuilder();
        urlBuilder.addQueryParameter("sessionKey", LocalStoreHelper.currentEmployee.sessionKey)
                .addQueryParameter("id", String.valueOf(LocalStoreHelper.currentEmployee.id))
                .addQueryParameter("orderId", String.valueOf(order.getId()));
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .build();

        try(Response res = client.newCall(request).execute()){
            JSONObject json = new JSONObject(res.body().string());
            return order.parseFoodList(json.getJSONArray("foods"));
        }
        catch (Exception e){
            return null;
        }
    }
    public static Order updateOrderStatus(Order order){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("sessionKey", LocalStoreHelper.currentEmployee.sessionKey)
                .add("orderId", String.valueOf(order.getId()))
                .add("status", "Выполнен")
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/%s", URL, "order/status-change"))
                .header("Content-Type","application/json")
                .put(body)
                .build();
        try(Response res = client.newCall(request).execute()){
            int code = res.code();
            if (code != 200) return null;
            return order;
        } catch (Exception e){
            return null;
        }
    }
}
