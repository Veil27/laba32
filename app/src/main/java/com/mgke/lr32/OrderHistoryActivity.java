package com.mgke.lr32;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = dbHelper.getAllOrders();
        if (cursor != null) {
            orderAdapter = new OrderAdapter(cursor);
            recyclerView.setAdapter(orderAdapter);
        } else {
            Toast.makeText(this, "Нет заказов", Toast.LENGTH_SHORT).show();
        }
    }
}
