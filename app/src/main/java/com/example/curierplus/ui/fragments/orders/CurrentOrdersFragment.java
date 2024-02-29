package com.example.curierplus.ui.fragments.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.curierplus.R;
import com.example.curierplus.api.OrdersAPI;
import com.example.curierplus.databinding.FragmentCurrentOrdersBinding;
import com.example.curierplus.enities.Order;
import com.example.curierplus.ui.adapters.OrdersAdapter;

import java.util.LinkedList;
import java.util.List;

/*
* Сделать панель выезжающей + реализовать заказы и профиль работника
* и готово.
* */
public class CurrentOrdersFragment extends Fragment {

    FragmentCurrentOrdersBinding binding;
    OrdersAdapter adapter;
    //List<Order> orderList;
    MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    public CurrentOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentOrdersBinding.inflate(inflater, container, false);
        //panelBinding = binding.panel;
        //optionsPanelService = new OptionsPanelService(binding.panel, getParentFragmentManager());
        //optionsPanelService.initOptions();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statusObserverInit();
        loadOrders();
    }
    public void loadOrders(){
        Runnable loadOrdersRnb = () -> {
            orders.postValue(OrdersAPI.loadOrdersForEmployee());
        };
        new Thread(loadOrdersRnb).start();
    }
    public void statusObserverInit(){
        orders.observe(getViewLifecycleOwner(), orders -> {
            binding.progress.setVisibility(View.GONE);
            if (orders == null){
                Toast.makeText(getContext(), "Заказы не найдены, перезайдите на страницу", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter = new OrdersAdapter(getContext(), R.layout.order_item, orders);
            binding.ordersList.setAdapter(adapter);
        });
    }
}