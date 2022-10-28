package com.moutamid.trivialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import com.moutamid.trivialapp.SharedPreferences;
import com.moutamid.trivialapp.adapters.CategoryAdapter;
import com.moutamid.trivialapp.database.CategoryDB;
import com.moutamid.trivialapp.dialog.UnlockDialog;
import com.moutamid.trivialapp.listners.CategoryClickListner;
import com.moutamid.trivialapp.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView catagoriesRC;
    List<CategoryModel> categorylist;
    CategoryAdapter adapter;
    CategoryDB database;
    TextView coinsTV;
    UnlockDialog dialog;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catagoriesRC = findViewById(R.id.catagoriesRC);
        coinsTV = findViewById(R.id.totalCoins);

        categorylist = new ArrayList<>();

        // Storing data into SharedPreferences
        sharedPreferences = new SharedPreferences(this);
        coinsTV.setText(""+sharedPreferences.getCoin());

        database = CategoryDB.getInstance(this);
        try {
            categorylist = database.CategoryDAO().getAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (categorylist.isEmpty()){
            CategoryModel category1, category2, category3, category4, category5;

            category1 = new CategoryModel("Mathematics", true, R.drawable.math);
            category2 = new CategoryModel("Puzzle", true, R.drawable.jigsaw);
            category3 = new CategoryModel("Science", true, R.drawable.science);
            category4 = new CategoryModel("Languages", true, R.drawable.languages);
            category5 = new CategoryModel("General Knowledge", true, R.drawable.book);

            database.CategoryDAO().insert(category1);
            database.CategoryDAO().insert(category2);
            database.CategoryDAO().insert(category3);
            database.CategoryDAO().insert(category4);
            database.CategoryDAO().insert(category5);

            categorylist.clear();
            categorylist.addAll(database.CategoryDAO().getAll());
            adapter.notifyDataSetChanged();

        }

        catagoriesRC.setLayoutManager(new LinearLayoutManager(this));
        catagoriesRC.setHasFixedSize(false);

        adapter = new CategoryAdapter(this, categorylist, clickListner);
        catagoriesRC.setAdapter(adapter);

    }

    private CategoryClickListner clickListner = new CategoryClickListner() {
        @Override
        public void onClick(CategoryModel category) {
            if (!category.isLockState()){
                if (category.getCategoryName().contains("Math")){
                    Intent intent = new Intent(MainActivity.this, QuestionsMathActivity.class);
                    startActivity(intent);
                }
            } else {
                dialog = new UnlockDialog(MainActivity.this, category, categorylist, adapter, coinsTV, sharedPreferences);
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        int s1 = sharedPreferences.getCoin();
        coinsTV.setText(String.valueOf(s1));
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.saveCoin(sharedPreferences.getCoin());
    }

}