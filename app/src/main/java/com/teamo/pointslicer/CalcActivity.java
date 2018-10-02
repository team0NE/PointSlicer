package com.teamo.pointslicer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class CalcActivity extends Activity{
    private EditText mPresentSale;
    private EditText mTotalSale;
    private Button mButton;
    private TextView mToast;

    private int preSale;
    private int totSale;
    private int presentDate;
    private int amountOfDays;
    private int salesPerDay;
    private int mDateSales;

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
                preSale = Integer.parseInt(mPresentSale.getText().toString());
                totSale = Integer.parseInt(mTotalSale.getText().toString());

                presentDate = Calendar.getInstance().get(Calendar.DATE);
                amountOfDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

                salesPerDay = totSale/amountOfDays;
                mDateSales = salesPerDay * presentDate;
                int difference = ((mDateSales - preSale) * -1);
                String text = "щоденне виконання має складати: " + salesPerDay + "\n" + "продажі на дату мають бути: " + mDateSales;

                mToast = findViewById(R.id.toast);
                mToast.setVisibility(View.VISIBLE);
                mToast.setText(text);
            }
        });
    }
}
