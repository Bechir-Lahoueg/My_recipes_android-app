package com.example.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addRecipes extends AppCompatActivity {
    private Button addButton;
    private EditText recipeName, description;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_recipes);


        db = FirebaseFirestore.getInstance();

        // Bind views
        addButton = findViewById(R.id.addButton);
        recipeName = findViewById(R.id.recipeName);
        description = findViewById(R.id.description);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recipeNameText = recipeName.getText().toString().trim();
                String descriptionText = description.getText().toString().trim();

                if (recipeNameText.isEmpty() || descriptionText.isEmpty()) {
                    Toast.makeText(addRecipes.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                Map<String, Object> recipe = new HashMap<>();
                recipe.put("recipeName", recipeNameText);
                recipe.put("description", descriptionText);


                db.collection("recipes")
                        .add(recipe)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(addRecipes.this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(addRecipes.this , breakfast.class) ;
                            startActivity(i);
                            recipeName.setText("");
                            description.setText("");
                        })
                        .addOnFailureListener(e -> Toast.makeText(addRecipes.this, "Failed to add recipe: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}