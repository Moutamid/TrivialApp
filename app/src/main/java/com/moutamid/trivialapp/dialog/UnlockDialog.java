package com.moutamid.trivialapp.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.moutamid.trivialapp.BuyActivity;
import com.moutamid.trivialapp.R;
import com.moutamid.trivialapp.SharedPreferences;
import com.moutamid.trivialapp.adapters.CategoryAdapter;
import com.moutamid.trivialapp.database.CategoryDB;
import com.moutamid.trivialapp.listners.CategoryClickListner;
import com.moutamid.trivialapp.models.CategoryModel;

import java.util.List;


public class UnlockDialog extends Dialog implements android.view.View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes, no, buy;
    CategoryModel category;
    List<CategoryModel> list;
    CategoryAdapter adapter;
    CategoryDB database;
    TextView coins;
    int coinN;
    SharedPreferences preferences;

    public UnlockDialog(Activity a, CategoryModel category, List<CategoryModel> list,
                        CategoryAdapter adapter, TextView coins, SharedPreferences preferences) {
        super(a);
        this.c = a;
        this.category = category;
        this.list = list;
        this.adapter = adapter;
        database = CategoryDB.getInstance(c);
        this.coins = coins;
        this.preferences = preferences;
        coinN = preferences.getCoin();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.unlock_category_dialog);

        yes = (Button) findViewById(R.id.btnYes);
        no = (Button) findViewById(R.id.btnCancel);
        buy = (Button) findViewById(R.id.btnBUY);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnYes:
                if (coinN >= 25){
                    category.setLockState(false);
                    database.CategoryDAO().update(category.getID(), category.getCategoryName(), category.isLockState(), category.getImage());
                    list.clear();
                    list.addAll(database.CategoryDAO().getAll());
                    adapter.notifyDataSetChanged();
                    coinN = coinN - 25;
                    coins.setText(""+coinN);
                    preferences.saveCoin(coinN);
                } else {
                    Toast.makeText(c.getApplicationContext(), "Not Enough Coins", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnBUY:
                c.startActivity(new Intent(c, BuyActivity.class));
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}