package com.example.curierplus.ui.fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curierplus.R;
import com.example.curierplus.api.OrdersAPI;
import com.example.curierplus.databinding.FragmentProfileBinding;
import com.example.curierplus.enities.Order;
import com.example.curierplus.local.DateHelper;
import com.example.curierplus.local.LocalStoreHelper;

import java.util.List;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.fullName.setText(LocalStoreHelper.currentEmployee.getFullName());
        binding.workStatus.setText(String.format(getString(R.string.working_status),"Работает"));
        binding.lastAuthDate.setText(String.format(getString(R.string.last_auth_date), LocalStoreHelper.currentEmployee.getLastAuth()));
        binding.completedOrdersSession.setText(
                String.format(getString(R.string.completed_orders_today),
                                        LocalStoreHelper.workActivity.ordersComplete));
        downloadOrders();
    }
    private void downloadOrders(){
        new Thread(() -> {
            binding.ordersTodayLoad.setVisibility(View.VISIBLE);
            orders.postValue(OrdersAPI.loadOrdersForEmployee());
        }).start();
        orders.observe(getViewLifecycleOwner(), orders -> {
            binding.ordersTodayLoad.setVisibility(View.GONE);
            binding.remainOrdersToday.setText(String.format(getString(R.string.total_orders_today), DateHelper.getCurrentOrdersCount(orders)));
        });
    }
}