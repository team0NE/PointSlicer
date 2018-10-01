package com.teamo.pointslicer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class CalcActivity extends Activity{
    private EditText mPresentSale;
    private EditText mTotalSale;
    private TextView mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        onClick();
    }

    public void onClick () {
        mPresentSale = findViewById(R.id.presentSalesAmount);
        int preSale = Integer.parseInt(mPresentSale.getText().toString());

        mTotalSale = findViewById(R.id.totalSalesAmount);
        int totSale = Integer.parseInt(mTotalSale.getText().toString());

        int presentDate = Calendar.getInstance().get(Calendar.DATE);
        int amountOfDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

        int SalesPerDay = totSale/amountOfDays;
        int mDateSales = SalesPerDay * presentDate;
        int difference = ((mDateSales - preSale) * -1);

        mToast = findViewById(R.id.toast);
        mToast.setVisibility(View.VISIBLE);
    }
}
