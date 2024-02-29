package com.example.curierplus.enities;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Order {
   String time;
   float price;
   String status;
   String clientFullName;
   String phone;
   String [] foodList;

    public Order(String time, float price, String status, String clientFullName, String phone) {
        this.time = time;
        this.price = price;
        this.status = status;
        this.clientFullName = clientFullName;
        this.phone = phone;
    }
    public Order(){}
    public static List<Order> parseOrdersArray(JSONArray ordersJson) throws JSONException {
        Gson gson = new Gson();
        List<Order> orders = new LinkedList<>();
        for(int i = 0; i < ordersJson.length();i++){
            JSONObject orderJson = ordersJson.getJSONObject(i);
            Order order = new Order();
            order.setTime(orderJson.getString("date"));
            order.setPrice(Float.valueOf(orderJson.getString("result_price")));
            order.setStatus(orderJson.getString("status_str"));
            order.setClientFullName(String.format("%s %s %s",
                    orderJson.getString("second_name"),
                    orderJson.getString("name"),
                    orderJson.getString("patronymic")));
            order.setPhone(orderJson.getString("phone"));
            orders.add(order);
        }
        return orders;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String[] getFoodList() {
        return foodList;
    }

    public void setFoodList(String[] foodList) {
        this.foodList = foodList;
    }
}
