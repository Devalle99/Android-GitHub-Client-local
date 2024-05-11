package com.davidvallejobravo.musicalbums;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.RepositoryAdapter;
import database.RepositoryDatabaseHelper;
import models.Repository;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Repository> repositories;
    private RepositoryAdapter repositoryAdapter;

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

        recyclerView = findViewById(R.id.recyclerView);

        this.loadRepositories();
    }

    public void loadRepositories() {
        RepositoryDatabaseHelper repoDbHelper = new RepositoryDatabaseHelper(this);

        repositories = new ArrayList<>();
        repositories.add(new Repository(1, "Repo 1", "Java", "David", ""));
        repositories.add(new Repository(2, "Repo 2", "Java", "David", ""));
        repositories.add(new Repository(3, "Repo 3", "Java", "David", ""));
        repositories.add(new Repository(4, "Repo 4", "Java", "David", ""));

        repositoryAdapter = new RepositoryAdapter(repositories, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repositoryAdapter);
    }
}