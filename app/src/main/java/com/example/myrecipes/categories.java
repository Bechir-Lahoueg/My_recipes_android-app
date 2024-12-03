package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class categories extends AppCompatActivity {


    private Button addCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        Button addCategory = findViewById(R.id.addCategory);

        addCategory.setOnClickListener((v -> {
            Intent intent = new Intent(categories.this, addcategory.class);
            startActivity(intent);
        })) ;

    }


}