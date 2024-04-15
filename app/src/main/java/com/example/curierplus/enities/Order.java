package com.example.curierplus.enities;

import com.example.curierplus.local.DateHelper;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable {
    public int id;
    @SerializedName("date")
   String time;
    @SerializedName("result_price")
   float price;
    @SerializedName("status_str")
   String status;
   String clientFullName;
   String phone;
   List<Food> foodList;

    public Order(String time, float price, String status, String clientFullName, String phone) {
        this.time = time;
        this.price = price;
        this.status = status;
        this.clientFullName = clientFullName;
        this.phone = phone;
    }
    public Order(){}
    public static List<Order> parseOrdersArray(JSONArray array) throws JSONException {
        Gson gson = new Gson();
        List<Order> orders = new LinkedList<>();
        for(int i = 0; i < array.length();i++){
            JSONObject orderJson = array.getJSONObject(i);
            Order order = gson.fromJson(orderJson.toString(), Order.class);
            order.setClientFullName(String.format("%s %s %s",
                    orderJson.getString("second_name"),
                    orderJson.getString("name"),
                    orderJson.getString("patronymic")));
            orders.add(order);
        }
        return orders;
    }
    public List<Food> parseFoodList(JSONArray array) throws JSONException {
        Gson gson = new Gson();
        List<Food> foods = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject foodJson = array.getJSONObject(i);
            Food food = gson.fromJson(foodJson.toString(), Food.class);
            foods.add(food);
        }
        this.foodList = foods;
        return foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return DateHelper.getTimeFromDate(time);
    }
    public String getFullDate(){
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
