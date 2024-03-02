package com.example.curierplus.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.curierplus.api.OrdersAPI;
import com.example.curierplus.databinding.FoodListDialogBinding;
import com.example.curierplus.enities.Food;
import com.example.curierplus.enities.Order;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class FoodListDialog extends DialogFragment {

    public static FoodListDialog getInstance(Order order){
        Bundle args = new Bundle();
        args.putSerializable("order", order);
        FoodListDialog dialog = new FoodListDialog();
        dialog.setArguments(args);
        return dialog;
    }
    FoodListDialogBinding binding;
    Order order;
    MutableLiveData<List<Food>> foodListObserver = new MutableLiveData<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            Gson gson = new Gson();
            String orderJson = gson.toJson(getArguments().get("order"));
            order = gson.fromJson(orderJson, Order.class);
        }
        binding = FoodListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showListObserverInit();
        loadFoodByOrder(order);
    }
    private void loadFoodByOrder(Order order){
        if(order.getFoodList() != null){
            foodListObserver.setValue(order.getFoodList());
            return;
        }
        new Thread(() -> {
            foodListObserver.postValue(OrdersAPI.loadFoodByOrder(order));
        }).start();
    }
    private void showListObserverInit(){
        foodListObserver.observe(getViewLifecycleOwner(), food -> {
            binding.progress.setVisibility(View.GONE);
            if(food == null){
                Toast.makeText(getContext(),
                        "Не удалось загрузить состав заказа",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String[] foodNames = food.stream()
                    .map(Food::getName)
                    .toArray(String[]::new);
            if(foodNames.length == 0)
                foodNames = new String[]{"Нет записи"};

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getContext(), android.R.layout.simple_list_item_1, foodNames);
            binding.foodList.setAdapter(adapter);
        });
    }
}
