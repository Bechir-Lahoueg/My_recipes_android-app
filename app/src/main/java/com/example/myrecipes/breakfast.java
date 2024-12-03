package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class breakfast extends AppCompatActivity {
private Button addRecipes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_breakfast);
         Button addRecipes = findViewById(R.id.addRecipes) ;


         addRecipes.setOnClickListener((v -> {
             Intent intent = new Intent(breakfast.this, addRecipes.class);
             startActivity(intent);
         })) ;
    }
}