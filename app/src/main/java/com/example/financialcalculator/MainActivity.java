package com.example.financialcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private CardView bankButtonDialog;
    private CardView emiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emiButton = findViewById(R.id.emiButton);
        emiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class).putExtra("key",3));
            }
        });

        bankButtonDialog = findViewById(R.id.bankButton);
        bankButtonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Here set custom dialog
        dialog.setContentView(R.layout.dialog_design_demo);

        CardView button_dialog_FixedDeposit = dialog.findViewById(R.id.buttonOfFixedDiposit);
        button_dialog_FixedDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class).putExtra("key", 1));
                dialog.dismiss();

            }

        });

//        dialog.show();

        CardView button_dialog_ProvidentFund = dialog.findViewById(R.id.buttonOfProvidentFund);
        button_dialog_ProvidentFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class).putExtra("key", 2));
                dialog.dismiss();

            }
        });

        dialog.show();
    }

}
