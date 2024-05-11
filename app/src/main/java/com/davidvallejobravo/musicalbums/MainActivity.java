package com.davidvallejobravo.musicalbums;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapters.RepositoryAdapter;
import database.RepositoryDatabaseHelper;
import models.Repository;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Repository> repositories;
    private RepositoryAdapter repositoryAdapter;
    private FloatingActionButton fabNewRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fabNewRepository = findViewById(R.id.fabNewRepository);
        recyclerView = findViewById(R.id.recyclerView);
        fabNewRepository.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RepositoryFormActivity.class);
                startActivity(intent);
            }
        });
        this.loadRepositories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.loadRepositories();
    }

    public void loadRepositories() {
        RepositoryDatabaseHelper repoDbHelper = new RepositoryDatabaseHelper(this);
        repositories = repoDbHelper.getRepos();
        repositoryAdapter = new RepositoryAdapter(repositories, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repositoryAdapter);
    }
}