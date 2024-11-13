package com.mgke.lr32;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Cursor cursor;

    public OrderAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            // Используем геттер для доступа к COLUMN_DESCRIPTION
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.getColumnDescription()));
            holder.orderDescription.setText(description);
        }
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDescription;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderDescription = itemView.findViewById(android.R.id.text1);
        }
    }
}
