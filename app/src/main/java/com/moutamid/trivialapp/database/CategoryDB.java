package com.moutamid.trivialapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.moutamid.trivialapp.models.CategoryModel;

@Database(entities = CategoryModel.class, version = 1, exportSchema = false)
public abstract class CategoryDB extends RoomDatabase {
    private static CategoryDB database;
    private static String DATABASE_NAME = "TrivialAPP";

    public synchronized static CategoryDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), CategoryDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract CategoryDAO CategoryDAO();

}
