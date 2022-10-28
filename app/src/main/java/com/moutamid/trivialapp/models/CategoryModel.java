package com.moutamid.trivialapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "CategoryTable")
public class CategoryModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name = "categoryName")
    String categoryName = "";

    @ColumnInfo(name = "lockState")
    boolean lockState = true;

    @ColumnInfo(name = "image")
    int image = 0;

    public CategoryModel(String categoryName, boolean lockState, int image) {
        this.categoryName = categoryName;
        this.lockState = lockState;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isLockState() {
        return lockState;
    }

    public void setLockState(boolean lockState) {
        this.lockState = lockState;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
