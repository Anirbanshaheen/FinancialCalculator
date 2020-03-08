package com.example.financialcalculator;

import android.content.Intent;
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


public class Fragment_fixed extends Fragment {

    private ImageView imageViewBack, imageViewYear, imageViewMonth;
    private EditText editTextPrincipalAmount, editTextInterestRate, editTextLoanTenure;
    private Button buttonCalculate, buttonReset,buttonShare;
    private TextView textViewInterest, textViewPrincipal, textViewResult, textViewShareInterestPayable, textViewSharePrincipal;
    private int value = 1;
    double principal,interest,tenure,calculate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixed, container, false);
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

        editTextPrincipalAmount = view.findViewById(R.id.edit_principalAmount);
        editTextInterestRate = view.findViewById(R.id.edit_interestRate);
        editTextLoanTenure = view.findViewById(R.id.edit_loanTenure);

        buttonCalculate = view.findViewById(R.id.bt_calculate);
        buttonReset = view.findViewById(R.id.bt_reset);
        buttonShare = view.findViewById(R.id.shareResult);

        textViewInterest = view.findViewById(R.id.textView_interestPayble);
        textViewPrincipal = view.findViewById(R.id.textView_principal);
        textViewResult = view.findViewById(R.id.textView_total_payment);

        textViewShareInterestPayable = view.findViewById(R.id.textView_interestPayble);
        textViewSharePrincipal = view.findViewById(R.id.textView_principal);

        imageViewYear = view.findViewById(R.id.year);
        imageViewMonth = view.findViewById(R.id.month);

        imageViewYear.setVisibility(View.VISIBLE);
        imageViewMonth.setVisibility(View.INVISIBLE);

        imageViewYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 2;
                imageViewYear.setVisibility(View.INVISIBLE);
                imageViewMonth.setVisibility(View.VISIBLE);

                editTextLoanTenure.getText().clear();
            }
        });

        imageViewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 1;
                imageViewYear.setVisibility(View.VISIBLE);
                imageViewMonth.setVisibility(View.INVISIBLE);
                editTextLoanTenure.getText().clear();
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String principalAmount = editTextPrincipalAmount.getText().toString();
                String interestRate = editTextInterestRate.getText().toString();
                String loanTenure = editTextLoanTenure.getText().toString();

                if (principalAmount.isEmpty() && interestRate.isEmpty() && loanTenure.isEmpty()) {
                    showCustomToast();

                } else if(value == 1) {

                    principal = Double.valueOf(principalAmount);
                    interest = (Double.valueOf(interestRate)) / 100;
                    tenure = Double.valueOf(loanTenure);

                    calculate = principal * (Math.pow((1 + (interest / 4)), (4 * tenure)));

                    textViewInterest.setText(String.format("%.2f", calculate - principal));
                    textViewPrincipal.setText(String.format("%.2f", principal));
                    textViewResult.setText(String.format("%.2f", calculate));

                } else if(value == 2) {

                    double principal = Double.valueOf(principalAmount);
                    double interest = (Double.valueOf(interestRate)) / 100;
                    double tenure = Double.valueOf(loanTenure) / 12;

                    double calculate = principal * (Math.pow((1 + (interest / 4)), (4 * tenure)));

                    textViewInterest.setText(String.format("%.2f", calculate - principal));
                    textViewPrincipal.setText(String.format("%.2f", principal));
                    textViewResult.setText(String.format("%.2f", calculate));

                }


            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPrincipalAmount.setText(null);
                editTextInterestRate.setText(null);
                editTextLoanTenure.setText(null);

                textViewInterest.setText(null);
                textViewPrincipal.setText(null);
                textViewResult.setText(null);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND).putExtra("Interest",principal);
                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Write your subject here");
                startActivity(Intent.createChooser(sharingIntent,"Share calculation"));
            }
        });




    }

    private void showCustomToast() {
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
