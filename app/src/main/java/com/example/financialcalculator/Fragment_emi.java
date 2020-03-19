package com.example.financialcalculator;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_emi extends Fragment {


    private EditText editTextEmiPrincipalAmount, editTextEmiInterestRate, editTextEmiLoanTenure;
    private Button buttonEmiCalculate, buttonEmiReset, buttonEmiShareResult;
    private TextView textViewEmiInterestPayable, textViewEmiPrincipal, textViewEmiPayment, textViewEmiTotalPayment;
    private int value = 1;

    private ImageView imageViewBack, imageViewYear, imageViewMonth;
    double principal, interest, tenure, calculate;

    public Fragment_emi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewBack = view.findViewById(R.id.backButton);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        editTextEmiPrincipalAmount = view.findViewById(R.id.edit_principalAmount);
        editTextEmiInterestRate = view.findViewById(R.id.edit_interestRate);
        editTextEmiLoanTenure = view.findViewById(R.id.edit_loanTenure);

        buttonEmiCalculate = view.findViewById(R.id.bt_calculate);
        buttonEmiReset = view.findViewById(R.id.bt_reset);
        buttonEmiShareResult = view.findViewById(R.id.shareResult);


        textViewEmiPrincipal = view.findViewById(R.id.textView_principal);


        textViewEmiTotalPayment = view.findViewById(R.id.textView_total_payment);

        imageViewMonth = view.findViewById(R.id.month);
        imageViewYear = view.findViewById(R.id.year);

        imageViewYear.setVisibility(View.VISIBLE);
        imageViewMonth.setVisibility(View.INVISIBLE);

        imageViewYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 2;
                imageViewYear.setVisibility(View.INVISIBLE);
                imageViewMonth.setVisibility(View.VISIBLE);
                editTextEmiLoanTenure.getText().clear();
            }
        });

        imageViewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 1;
                imageViewYear.setVisibility(View.VISIBLE);
                imageViewMonth.setVisibility(View.INVISIBLE);
                editTextEmiLoanTenure.getText().clear();
            }
        });

        buttonEmiCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String principalEmiAmount = editTextEmiPrincipalAmount.getText().toString();
                String interestRateEmi = editTextEmiInterestRate.getText().toString();
                String loanTenureEmi = editTextEmiLoanTenure.getText().toString();

                if (principalEmiAmount.isEmpty() && interestRateEmi.isEmpty() && loanTenureEmi.isEmpty()) {
                    showCustomToastEmi();
                } else if (value == 1) {
                    // for year
                    principal = Double.valueOf(principalEmiAmount);
                    interest = ((Double.valueOf(interestRateEmi)) / 12) / 100;
                    tenure = (Double.valueOf(loanTenureEmi)) * 12;

                    calculate = (principal * interest * Math.pow((1 + interest), tenure)) / (Math.pow((1 + interest), tenure) - 1);

                    textViewEmiPrincipal.setText(String.format("%.2f", principal));
                    textViewEmiTotalPayment.setText(String.format("%.2f", calculate));
                } else if (value == 2) {
                    // for month
                    principal = Double.valueOf(principalEmiAmount);
                    interest = ((Double.valueOf(interestRateEmi)) / 12) / 100;
                    tenure = Double.valueOf(loanTenureEmi);

                    calculate = (principal * interest * Math.pow((1 + interest), tenure)) / (Math.pow((1 + interest), tenure) - 1);

                    textViewEmiPrincipal.setText(String.format("%.2f", principal));
                    textViewEmiTotalPayment.setText(String.format("%.2f", calculate));
                }
            }
        });

        buttonEmiReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmiPrincipalAmount.setText(null);
                editTextEmiInterestRate.setText(null);
                editTextEmiLoanTenure.setText(null);

                textViewEmiPrincipal.setText(null);
                textViewEmiTotalPayment.setText(null);
            }
        });

        buttonEmiShareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND).putExtra("Interest", principal);
                sharingIntent.setData(Uri.parse("mailto"));
                sharingIntent.putExtra(sharingIntent.EXTRA_SUBJECT, "Total Calculation");
                sharingIntent.putExtra(sharingIntent.EXTRA_TEXT, );
                sharingIntent.setType("text/plain");

                if (sharingIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(Intent.createChooser(sharingIntent, "Share calculation"));
                }
//                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Write your subject here");

            }
        });


    }

    private void showCustomToastEmi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getView().findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.customText);
        text.setText("Please fill the required field");

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
