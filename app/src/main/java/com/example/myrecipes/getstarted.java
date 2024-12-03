package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class getstarted extends AppCompatActivity {

    private Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_getstarted);


        getStarted = findViewById(R.id.button4);
        getStarted.setOnClickListener(v -> {
            Intent intent = new Intent(getstarted.this, categories.class);
            startActivity(intent);
        });

    }
}