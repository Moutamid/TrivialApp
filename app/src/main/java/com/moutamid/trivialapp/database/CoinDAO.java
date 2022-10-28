package com.moutamid.trivialapp.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.moutamid.trivialapp.models.CoinsModel;

import java.util.List;

@Dao
public interface CoinDAO {

/*    @Insert(onConflict = REPLACE)
    void insert(CoinsModel coinsModel);

    @Query("SELECT * FROM Coins")
    List<CoinsModel> getAll();

    @Query("UPDATE Coins SET coin = :coin")
    void update(int coin);

    @Delete
    void Delete(CoinsModel coinsModel);*/
}
