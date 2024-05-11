package database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        values.put(IMAGE, "https://images.unsplash.com/photo-1715045086640-09ceccc6fdcc?q=80&w=1528&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        db.insert(REPO_TABLE, null, values);

        values.put(NAME, "Repositorio 2");
        values.put(LANGUAGE, "Java");
        values.put(OWNER, "Homero Simpson");
        values.put(IMAGE, "https://images.unsplash.com/photo-1715157163446-91abdd457a97?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
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

        String[] columns = {ID, NAME, LANGUAGE, OWNER, IMAGE};

        Cursor cursor = db.query(REPO_TABLE, columns, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME));
                    @SuppressLint("Range") String language = cursor.getString(cursor.getColumnIndex(LANGUAGE));
                    @SuppressLint("Range") String owner = cursor.getString(cursor.getColumnIndex(OWNER));
                    @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(IMAGE));

                    repositories.add(new Repository(id, name, language, owner, image));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return repositories;
    }
}
