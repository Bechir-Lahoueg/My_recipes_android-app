package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class categories extends AppCompatActivity {

    private Button addCategory;
    private LinearLayout categoryContainer; // Container to hold the dynamically added buttons
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Find views
        addCategory = findViewById(R.id.addCategory);
        categoryContainer = findViewById(R.id.categoryContainer);

        // Add category button click listener
        addCategory.setOnClickListener(v -> {
            Intent intent = new Intent(categories.this, addcategory.class);
            startActivity(intent);
        });

        // Fetch and display categories
        fetchCategories();
    }

    private void fetchCategories() {
        db.collection("categories")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    categoryContainer.removeAllViews(); // Clear any existing views
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String categoryName = document.getString("categoryName");

                        if (categoryName != null) {
                            // Dynamically create a button for each category
                            Button categoryButton = new Button(this);
                            categoryButton.setText(categoryName);
                            categoryButton.setOnClickListener(v -> {
                                Toast.makeText(categories.this, "Clicked: " + categoryName, Toast.LENGTH_SHORT).show();
                                // Handle button click here
                            });

                            // Add the button to the container
                            categoryContainer.addView(categoryButton);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Categories", "Error fetching categories", e);
                    Toast.makeText(this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
                });
    }
}
