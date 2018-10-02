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
    private int SalesPerDay;
    private int mDateSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        mPresentSale = findViewById(R.id.presentSalesAmount);
        mTotalSale = findViewById(R.id.totalSalesAmount);
        mButton = findViewById(R.id.calculate);

        onClick();
    }

    public void onClick () {
        mPresentSale = findViewById(R.id.presentSalesAmount);
        preSale = Integer.parseInt(mPresentSale.getText().toString());

        mTotalSale = findViewById(R.id.totalSalesAmount);
        totSale = Integer.parseInt(mTotalSale.getText().toString());

        presentDate = Calendar.getInstance().get(Calendar.DATE);
        amountOfDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

        SalesPerDay = totSale/amountOfDays;
        mDateSales = SalesPerDay * presentDate;
        int difference = ((mDateSales - preSale) * -1);

        mToast = findViewById(R.id.toast);
        mToast.setVisibility(View.VISIBLE);
    }
}
