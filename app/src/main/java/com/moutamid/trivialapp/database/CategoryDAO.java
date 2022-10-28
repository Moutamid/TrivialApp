package com.moutamid.trivialapp.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.moutamid.trivialapp.models.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = REPLACE)
    void insert(CategoryModel categoryModel);

    @Query("SELECT * FROM CategoryTable ORDER BY ID ASC")
    List<CategoryModel> getAll();

    @Query("UPDATE CategoryTable SET categoryName = :categoryName, lockState = :lockState, image= :image where ID = :id")
    void update(int id, String categoryName, boolean lockState, int image);

    @Delete
    void Delete(CategoryModel categoryModel);
}
