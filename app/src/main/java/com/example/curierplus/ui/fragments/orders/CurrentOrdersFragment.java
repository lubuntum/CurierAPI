package com.example.curierplus.ui.fragments.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.curierplus.R;
import com.example.curierplus.api.OrdersAPI;
import com.example.curierplus.databinding.FragmentCurrentOrdersBinding;
import com.example.curierplus.enities.Order;
import com.example.curierplus.local.LocalStoreHelper;
import com.example.curierplus.ui.adapters.OrdersAdapter;
import com.example.curierplus.ui.dialog.FoodListDialog;

import java.util.List;

/*
* Сделать панель выезжающей + реализовать заказы и профиль работника
* и готово.
* */
public class CurrentOrdersFragment extends Fragment
        implements OrdersAdapter.ViewHolder.OnBtnClickListener,
                    OrdersAdapter.ViewHolder.OnCompleteOrderClickListener{

    FragmentCurrentOrdersBinding binding;
    OrdersAdapter adapter;
    Order lastCompleteOrder = null;
    //List<Order> orderList;
    MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    MutableLiveData<Order> orderCompleteStatus = new MutableLiveData<>();
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
        changeStatusObserverInit();
        ordersObserverInit();
        loadOrders();
    }
    public void loadOrders(){
        Runnable loadOrdersRnb = () -> {
            orders.postValue(OrdersAPI.loadOrdersForEmployee());
        };
        new Thread(loadOrdersRnb).start();
    }
    public void ordersObserverInit(){
        orders.observe(getViewLifecycleOwner(), orders -> {
            binding.progress.setVisibility(View.GONE);
            if (orders == null){
                Toast.makeText(getContext(), "Заказы не найдены, перезайдите на страницу", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter = new OrdersAdapter(getContext(), R.layout.order_item, orders, this,this);
            binding.ordersList.setAdapter(adapter);
        });
    }
    public void changeStatusObserverInit(){
        orderCompleteStatus.observe(getViewLifecycleOwner(), order -> {
            if(order == null){
                Toast.makeText(getContext(), "Не удалось изменить статус, попробуйте еще раз", Toast.LENGTH_SHORT).show();
                return;
            }
            LocalStoreHelper.workActivity.ordersComplete++;
            LocalStoreHelper.saveWorkActivity();
            Toast.makeText(
                    getContext(),
                    String.format("Заказ для %s успешно закрыт", order.getClientFullName()),
                    Toast.LENGTH_SHORT).show();
            adapter.removeItem(order);
        });
    }

    @Override
    public void onClick(int pos) {
        FoodListDialog dialog = FoodListDialog.getInstance(adapter.getItemByPos(pos));
        dialog.show(getParentFragmentManager(), "food_list");
    }

    @Override
    public void completeOrder(int pos) {
        new Thread(()-> orderCompleteStatus.postValue(
                OrdersAPI.updateOrderStatus(adapter.getItemByPos(pos))))
                .start();
    }
}