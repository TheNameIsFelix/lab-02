package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayList<String> dataList;
    ArrayAdapter<String> cityAdapter;
    boolean deleteMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.list_view);
        String []cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>( this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (deleteMode) {
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
                deleteMode = false; // turn off delete mode
            }
        });



    }

    public void handleText(View v) {
        EditText textField = findViewById(R.id.textField);
        textField.setVisibility(View.VISIBLE);
        textField.setText("");
        TextView confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setVisibility(View.VISIBLE);


    }

    public void handleConfirm(View v) {
        EditText textField = findViewById(R.id.textField);
        String text = textField.getText().toString();
        dataList.add(text);
        cityAdapter.notifyDataSetChanged();
        textField.setVisibility(View.GONE);
        TextView confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setVisibility(View.GONE);

    }

    public void handleDelete(View v) {
        if (!dataList.isEmpty()) {
            deleteMode = true;
        }
    }

}