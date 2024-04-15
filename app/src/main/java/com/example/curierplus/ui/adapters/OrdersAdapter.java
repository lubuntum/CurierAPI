package com.example.curierplus.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curierplus.R;
import com.example.curierplus.enities.Order;

import java.util.List;
/*
* Дописать профиль, возможность просматривать состав заказа, возможность закрывать заказ
* */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private List<Order> orderList;
    private int resource;
    private LayoutInflater inflater;
    private ViewHolder.OnBtnClickListener foodListener;
    private ViewHolder.OnCompleteOrderClickListener completeOrderListener;
    public OrdersAdapter(Context context, int res,
                         List<Order> orders,
                         ViewHolder.OnBtnClickListener foodListener,
                         ViewHolder.OnCompleteOrderClickListener completeListener){
        this.orderList = orders;
        this.resource = res;
        this.inflater = LayoutInflater.from(context);
        this.foodListener = foodListener;
        this.completeOrderListener = completeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView, foodListener, completeOrderListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.fullName.setText(order.getClientFullName());
        holder.time.setText(String.format("Время заказа: %s",order.getTime()));
        holder.price.setText(String.format("Цена: %s руб.", String.valueOf(order.getPrice())));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public Order getItemByPos(int pos){
        return orderList.get(pos);
    }
    public void removeItem(Order order){
        int pos = orderList.indexOf(order);
        if (pos == -1) return;
        orderList.remove(order);
        notifyDataSetChanged();
        //notifyItemChanged(pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fullName;
        public TextView price;
        public TextView time;
        public TextView phone;
        public ImageView showFoodListBtn;
        public ImageView completeOrderBtn;
        public ViewHolder(@NonNull View itemView,
                          OnBtnClickListener foodListener,
                          OnCompleteOrderClickListener completeListener) {
            super(itemView);
            fullName = itemView.findViewById(R.id.full_name);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            phone = itemView.findViewById(R.id.phone);
            showFoodListBtn = itemView.findViewById(R.id.show_food_list);
            completeOrderBtn = itemView.findViewById(R.id.complete_order);
            showFoodListBtn.setOnClickListener(view ->
                    foodListener.onClick(getAdapterPosition()));
            completeOrderBtn.setOnClickListener(v->
                    completeListener.completeOrder(getAdapterPosition()));
        }
        public interface OnBtnClickListener{
            void onClick(int pos);
        }
        public interface OnCompleteOrderClickListener{
            void completeOrder(int pos);
        }
    }
}
