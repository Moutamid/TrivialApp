package com.moutamid.trivialapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.moutamid.trivialapp.models.CoinsModel;

@Database(entities = CoinsModel.class, version = 1, exportSchema = false)
public abstract class CoinsDB extends RoomDatabase {
    private static CoinsDB database;
    private static String DATABASE_NAME = "TrivialAPP";

    public synchronized static CoinsDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), CoinsDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract CoinDAO coinDAO();

}
