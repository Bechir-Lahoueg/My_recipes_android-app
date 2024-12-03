package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class RecipesInCategory extends AppCompatActivity {

    private LinearLayout recipesContainer; // Container to hold the dynamically added recipe names
    private FirebaseFirestore db;
    private String categoryId;
    private Button addRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes_in_category);

        addRecipe = findViewById(R.id.addRecipe);
        addRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(RecipesInCategory.this, addRecipes.class);
            startActivity(intent);
        });

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Find views
        recipesContainer = findViewById(R.id.recipesContainer);

        // Get the category ID passed from the previous activity
        categoryId = getIntent().getStringExtra("CATEGORY_ID");

        if (categoryId != null) {
            // Fetch recipes for the selected category
            fetchRecipes(categoryId);
        } else {
            Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchRecipes(String categoryId) {
        db.collection("categories")
                .document(categoryId)
                .collection("recipes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        recipesContainer.removeAllViews(); // Clear any existing views
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String recipeName = document.getString("recipeName");
                            String description = document.getString("description");

                            if (recipeName != null) {
                                // Dynamically create a TextView for each recipe
                                TextView recipeTextView = new TextView(this);
                                recipeTextView.setText(recipeName);
                                recipeTextView.setText(description);

                                recipeTextView.setTextSize(18f);
                                recipeTextView.setPadding(16, 16, 16, 16);

                                // Add the recipe to the container
                                recipesContainer.addView(recipeTextView);
                            }
                        }
                    } else {
                        Toast.makeText(this, "No recipes found for this category", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("RecipesInCategory", "Error fetching recipes", e);
                    Toast.makeText(this, "Failed to fetch recipes", Toast.LENGTH_SHORT).show();
                });
    }
}
