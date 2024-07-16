package com.davidvallejobravo.musicalbums;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import database.RepositoryDatabaseHelper;
import models.Repository;

public class RepositoryFormActivity extends AppCompatActivity {

    private Button save;
    private EditText nameField;
    private EditText ownerField;
    private EditText lenguaje;
    private EditText image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repository_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        save = findViewById(R.id.buttonSave);
        nameField = findViewById(R.id.newNameField);
        ownerField = findViewById(R.id.newOwnerField);
        lenguaje = findViewById(R.id.newLanguageField);
        image = findViewById(R.id.newImageField);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String formName = nameField.getText().toString();
                String formOwner = ownerField.getText().toString();
                String formLanguage = lenguaje.getText().toString();
                String formImage = image.getText().toString();

                Repository repository = new Repository(0,formName, formLanguage, formOwner, formImage);

                RepositoryDatabaseHelper dbHelper = new RepositoryDatabaseHelper(RepositoryFormActivity.this);
                dbHelper.insertRepository(repository);

                Intent intent = new Intent(RepositoryFormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}