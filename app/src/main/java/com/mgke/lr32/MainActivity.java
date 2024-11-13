package com.mgke.lr32;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch tempSwitch;
    private ImageView tempImage;
    private CheckBox milkCheckBox, creamCheckBox, sugarCheckBox, syrupCheckBox;
    private RadioGroup deliveryGroup;
    private Button orderButton;  // Button import should now work
    private Button historyButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempSwitch = findViewById(R.id.tempSwitch);
        tempImage = findViewById(R.id.tempImage);
        milkCheckBox = findViewById(R.id.milkCheckBox);
        creamCheckBox = findViewById(R.id.creamCheckBox);
        sugarCheckBox = findViewById(R.id.sugarCheckBox);
        syrupCheckBox = findViewById(R.id.syrupCheckBox);
        deliveryGroup = findViewById(R.id.deliveryGroup);
        orderButton = findViewById(R.id.orderButton);
        historyButton = findViewById(R.id.historyButton);

        // Теперь этот код будет безопасно извлекать текст из радио-кнопки
        RadioButton radioButton = findViewById(R.id.radioButtonExample);
        if (radioButton != null) {
            String text = radioButton.getText().toString();
            Log.d("MainActivity", "Текст радио кнопки: " + text);
        } else {
            Log.e("MainActivity", "RadioButton не найден!");
        }

        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });

        // Обработчик для переключателя температуры
        tempSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tempImage.setImageResource(R.drawable.red_circle); // Горячий кофе (красный)
            } else {
                tempImage.setImageResource(R.drawable.blue_circle); // Холодный кофе (синий)
            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);

        orderButton.setOnClickListener(v -> {
            String temperature = tempSwitch.isChecked() ? "Горячий" : "Холодный";
            StringBuilder additives = new StringBuilder();
            if (milkCheckBox.isChecked()) additives.append("Молоко ");
            if (creamCheckBox.isChecked()) additives.append("Сливки ");
            if (sugarCheckBox.isChecked()) additives.append("Сахар ");
            if (syrupCheckBox.isChecked()) additives.append("Сироп ");

            // Получаем выбранный RadioButton из RadioGroup
            int selectedId = deliveryGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(MainActivity.this, "Пожалуйста, выберите способ доставки", Toast.LENGTH_SHORT).show();
                return; // Выход из метода, если ничего не выбрано
            }

            RadioButton selectedRadioButton = findViewById(selectedId);
            String delivery = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Не выбран";

            String orderDescription = "Кофе: " + temperature + "\nДобавки: " + additives.toString() + "\nСпособ доставки: " + delivery;

            // Добавление заказа в базу данных
            dbHelper.addOrder(orderDescription);

            Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
            intent.putExtra("orderDescription", orderDescription);
            startActivity(intent);
        });

    }
}
