package com.example.financialcalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


//        ActionBar actionBar= getSupportActionBar();
//        if(actionBar != null)
//        {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        int i = getIntent().getIntExtra("key", 1);


        if (i == 1) {
            fragmentManager.beginTransaction().replace(R.id.containerID, new Fragment_fixed()).commit();
        }
        if (i == 2) {
            fragmentManager.beginTransaction().replace(R.id.containerID, new Fragment_provident()).commit();
        }
        if (i == 3){
            fragmentManager.beginTransaction().replace(R.id.containerID, new Fragment_emi()).commit();
        }
    }

}
