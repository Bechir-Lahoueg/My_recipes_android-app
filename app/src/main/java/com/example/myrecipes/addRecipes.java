package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addRecipes extends AppCompatActivity {
    private Button addButton;
    private EditText recipeName, description;
    private Spinner categorySpinner;
    private FirebaseFirestore db;
    private ArrayList<String> categoryNames;
    private ArrayList<String> categoryIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipes);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Bind views
        addButton = findViewById(R.id.addButton);
        recipeName = findViewById(R.id.recipeName);
        description = findViewById(R.id.description);
        categorySpinner = findViewById(R.id.categorySpinner);

        // Fetch categories from Firestore
        fetchCategories();

        // Add recipe button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeNameText = recipeName.getText().toString().trim();
                String descriptionText = description.getText().toString().trim();
                int selectedCategoryPosition = categorySpinner.getSelectedItemPosition();

                if (recipeNameText.isEmpty() || descriptionText.isEmpty() || selectedCategoryPosition == -1) {
                    Toast.makeText(addRecipes.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedCategoryId = categoryIds.get(selectedCategoryPosition);

                // Create recipe data
                Map<String, Object> recipe = new HashMap<>();
                recipe.put("recipeName", recipeNameText);
                recipe.put("description", descriptionText);

                // Save the recipe under the selected category
                db.collection("categories")
                        .document(selectedCategoryId)
                        .collection("recipes")
                        .add(recipe)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(addRecipes.this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                            recipeName.setText("");
                            description.setText("");
                            Intent i = new Intent(addRecipes.this, categories.class);
                            startActivity(i);
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(addRecipes.this, "Failed to add recipe: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void fetchCategories() {
        categoryNames = new ArrayList<>();
        categoryIds = new ArrayList<>();

        db.collection("categories")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        categoryNames.add(document.getString("categoryName"));
                        categoryIds.add(document.getId());
                    }

                    // Set Spinner adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorySpinner.setAdapter(adapter);
                })
                .addOnFailureListener(e ->
                        Toast.makeText(addRecipes.this, "Failed to fetch categories: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
