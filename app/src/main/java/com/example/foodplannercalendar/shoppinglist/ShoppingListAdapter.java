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

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private ArrayList<ShoppingListItem> items;
    private OnClickListener onClickListener;

    public ShoppingListAdapter(ArrayList<ShoppingListItem> items) { this.items = items; }

    public void setOnClickListener(OnClickListener listener) { this.onClickListener = listener; }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shoppinglist_layout, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.amount.setText(valueOf(items.get(position).getAmount()));
    }

    public int getItemCount() { return items.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView amount;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            amount = itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(items.remove(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(ShoppingListItem item);
    }
}

