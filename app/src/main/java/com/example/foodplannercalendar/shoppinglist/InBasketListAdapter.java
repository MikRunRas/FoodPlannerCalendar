package com.example.foodplannercalendar.shoppinglist;


import static java.lang.String.valueOf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannercalendar.R;

import java.util.ArrayList;

public class InBasketListAdapter extends RecyclerView.Adapter<InBasketListAdapter.ViewHolder> {

    private ArrayList<ShoppingListItem> itemsBought;
    private InBasketListAdapter.OnClickListener onClickListener;

    public InBasketListAdapter(ArrayList<ShoppingListItem> itemsBought) { this.itemsBought = itemsBought; }

    public void setOnClickListener(InBasketListAdapter.OnClickListener listener) { this.onClickListener = listener; }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.in_basket_list_layout, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull InBasketListAdapter.ViewHolder holder, int position) {
        holder.title.setText(itemsBought.get(position).getTitle());
        holder.amount.setText(valueOf(itemsBought.get(position).getAmount()));
    }

    public int getItemCount() { return itemsBought.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView amount;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            amount = itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(itemsBought.remove(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(ShoppingListItem item);
    }
}