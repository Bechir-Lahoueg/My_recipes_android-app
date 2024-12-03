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

public class addcategory extends AppCompatActivity {

    private EditText categoryName;
    private FirebaseFirestore db;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addcategory);

        db = FirebaseFirestore.getInstance();
        categoryName = findViewById(R.id.categoryName);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryNameText = categoryName.getText().toString().trim();

                if (categoryNameText.isEmpty()) {
                    Toast.makeText(addcategory.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> category = new HashMap<>();
                category.put("categoryName", categoryNameText);

                db.collection("categories")
                        .add(category)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(addcategory.this, "Category added successfully!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(addcategory.this, categories.class);
                            startActivity(i);
                            categoryName.setText("");
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(addcategory.this, "Failed to add category: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
            }
        });
    }
}
