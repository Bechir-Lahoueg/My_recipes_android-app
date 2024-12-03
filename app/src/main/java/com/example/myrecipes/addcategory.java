package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addcategory extends AppCompatActivity {


    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addcategory);

        Button add = findViewById(R.id.add);

        add.setOnClickListener((v -> {
            Intent intent = new Intent(addcategory.this, categories.class);
            startActivity(intent);
        })) ;


    }


}