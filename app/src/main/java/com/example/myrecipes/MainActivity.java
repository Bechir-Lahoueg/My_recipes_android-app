package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Button breakfast;
    private Button lunch;
    private Button dinner;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    Button breakfast = findViewById(R.id.breakfast);

    breakfast.setOnClickListener(v -> {
        Intent intent = new Intent(MainActivity.this, breakfast.class);
        startActivity(intent);
    });




    }
}