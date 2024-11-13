package com.mgke.lr32;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private TextView orderDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderDescriptionTextView = findViewById(R.id.orderDescription);

        // Получаем описание заказа из Intent
        String orderDescription = getIntent().getStringExtra("orderDescription");

        // Устанавливаем описание в TextView
        orderDescriptionTextView.setText(orderDescription);
    }
}
