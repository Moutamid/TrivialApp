package com.moutamid.trivialapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;

public class BuyActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    BillingProcessor bp;
    SharedPreferences sharedPreferences;
    TextView coinsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        bp = BillingProcessor.newBillingProcessor(this, Constants.LICENSE_KEY, this);
        bp.initialize();

        coinsTV = findViewById(R.id.totalCoins);

        // Storing data into SharedPreferences
        sharedPreferences = new SharedPreferences(this);
        coinsTV.setText("" + sharedPreferences.getCoin());


        findViewById(R.id.buyBtn5).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.FIVE_DOLLAR_PRODUCT);
            buyFunc(5);
        });

        findViewById(R.id.buyBtn10).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.TEN_DOLLAR_PRODUCT);
            buyFunc(10);
        });
        findViewById(R.id.buyBtn20).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.TWENTY_DOLLAR_PRODUCT);
            buyFunc(20);
        });

        findViewById(R.id.buyBtn50).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.FIFTY_DOLLAR_PRODUCT);
            buyFunc(50);
        });
        findViewById(R.id.buyBtn100).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.HUNDRED_DOLLAR_PRODUCT);
            buyFunc(100);
        });
        findViewById(R.id.buyBtn200).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.TWO_HUNDRED_DOLLAR_PRODUCT);
            buyFunc(200);
        });

        findViewById(R.id.buyBtn300).setOnClickListener((View.OnClickListener) view -> {
            bp.purchase(BuyActivity.this, Constants.THREE_HUNDRED_DOLLAR_PRODUCT);
            buyFunc(300);
        });

    }

    public void buyFunc(int price){
        int s1 = sharedPreferences.getCoin();

        sharedPreferences.saveCoin(s1 + price);

        coinsTV.setText(String.valueOf(sharedPreferences.getCoin()));
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        Toast.makeText(BuyActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();

        if (productId.equals(Constants.HUNDRED_DOLLAR_PRODUCT)) {
            int s1 = sharedPreferences.getCoin();

            sharedPreferences.saveCoin(s1 + 25);

            coinsTV.setText(String.valueOf(sharedPreferences.getCoin()));
        }

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(BuyActivity.this, "onBillingError: code: " + errorCode + " \n" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

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