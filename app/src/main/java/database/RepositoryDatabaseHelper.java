package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import adapters.RepositoryAdapter;
import models.Repository;

public class RepositoryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "repositories.db";
    private static final int DB_VERSION = 1;
    public static final String REPO_TABLE = "repositories";

    // Columnas
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String OWNER = "owner";
    public static final String LANGUAGE = "language";
    public static final String IMAGE = "image";

    public RepositoryDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + REPO_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                OWNER + " TEXT, " +
                LANGUAGE + " TEXT, " +
                IMAGE + " TEXT" +  ");"
        );

        // Data inicial
        ContentValues values = new ContentValues();

        values.put(NAME, "repositorio 1");
        values.put(LANGUAGE, "Python");
        values.put(OWNER, "David Vallejo");
        values.put(IMAGE, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.cleanpng.com%2Fpng-python-logo-clojure-javascript-9-834158%2F&psig=AOvVaw1zkHKdRmOUpHQJgr4ycc-Z&ust=1715220444484000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJD93pz8_IUDFQAAAAAdAAAAABAH");
        db.insert(REPO_TABLE, null, values);

        values.put(NAME, "Repositorio 2");
        values.put(LANGUAGE, "Java");
        values.put(OWNER, "Homero Simpson");
        values.put(IMAGE, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.cleanpng.com%2Fpng-python-logo-clojure-javascript-9-834158%2F&psig=AOvVaw1zkHKdRmOUpHQJgr4ycc-Z&ust=1715220444484000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJD93pz8_IUDFQAAAAAdAAAAABAH");
        db.insert(REPO_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertRepository(Repository repository) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, repository.getRepositoryName());
        values.put(LANGUAGE, repository.getRepositoryLanguage());
        values.put(OWNER, repository.getRepositoryOwner());
        values.put(IMAGE, repository.getImage());

        long id = db.insert(REPO_TABLE, null, values);
        db.close();
        return id;
    }

    public List<Repository> getRepos() {
        List<Repository> repositories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        return repositories;
    }
}
