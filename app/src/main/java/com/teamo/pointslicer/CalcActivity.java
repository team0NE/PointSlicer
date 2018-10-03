package com.teamo.pointslicer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;


public class CalcActivity extends Activity{
    private EditText mPresentSale;
    private EditText mTotalSale;
    private Button mButton;
    private TextView mToast;

    private double preSale;
    private double totSale;
    private int presentDate;
    private int amountOfDays;
    private double salesPerDay;
    private double mDateSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        mPresentSale = findViewById(R.id.presentSalesAmount);
        mTotalSale = findViewById(R.id.totalSalesAmount);
        mButton = findViewById(R.id.calculate);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);

                preSale = Integer.parseInt(mPresentSale.getText().toString());
                preSale = Math.round(preSale * 100d)/100d;//(double)Math.round(value * 100000d) / 100000d
                totSale = Double.parseDouble(mTotalSale.getText().toString());// over the DecimalFormat
                totSale = Double.parseDouble(df.format(totSale));

                presentDate = Calendar.getInstance().get(Calendar.DATE);
                amountOfDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

                salesPerDay = totSale/amountOfDays;
                mDateSales = salesPerDay * presentDate;
                double difference = ((mDateSales - preSale) * -1);
                String text = "щоденне виконання має складати: " + salesPerDay + "\n" + "продажі на дату мають бути: " + mDateSales;

                mToast = findViewById(R.id.toast);
                mToast.setVisibility(View.VISIBLE);
                mToast.setText(text);
            }
        });
    }
}

// finish DecimalFormat
//finish input fields
// To do result activity
// To do clipboard method

// how todo work?