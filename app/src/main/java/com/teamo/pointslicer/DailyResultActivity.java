package com.teamo.pointslicer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

public class DailyResultActivity extends AppCompatActivity {
    private int presentDate;
    private int amountOfDays;
    private double salesPerDay;
    private double requiredAmount;
    private double difference;

    private double presentSale;
    private double totalSale;

    TextView twSalesPerDateResult;
    TextView twRequiredAmount;
    TextView twDifference;
    TextView twDifferenceResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_result_layout);
        if(savedInstanceState != null) {
            presentSale = savedInstanceState.getDouble("presentSale", presentSale);
            totalSale = savedInstanceState.getDouble("totalSale", totalSale);
            salesPerDay = savedInstanceState.getDouble("salesPerDay", salesPerDay);
            requiredAmount = savedInstanceState.getDouble("requiredAmount", requiredAmount);
            difference = savedInstanceState.getDouble("difference", difference);
        }

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        Intent receiveIntent = this.getIntent();
        presentSale = receiveIntent.getDoubleExtra("presentSale", presentSale);
        presentSale = Double.parseDouble(df.format(presentSale));
        totalSale = receiveIntent.getDoubleExtra("totalSale", totalSale);
        totalSale = Double.parseDouble(df.format(totalSale));

        //replace from input to here. some logic change
        presentDate = Calendar.getInstance().get(Calendar.DATE);
        amountOfDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

        salesPerDay = Double.parseDouble(df.format(totalSale /amountOfDays));
        twSalesPerDateResult = findViewById(R.id.sales_per_day_result);
        twSalesPerDateResult.setText(String.valueOf(salesPerDay));
        requiredAmount = salesPerDay * presentDate;
        requiredAmount = Double.parseDouble(df.format(requiredAmount));
        twRequiredAmount = findViewById(R.id.date_sales_result);
        twRequiredAmount.setText(String.valueOf(requiredAmount));

        difference = Double.parseDouble(df.format(((requiredAmount - presentSale) * -1)));

        if (requiredAmount > presentSale) {
            twDifference = findViewById(R.id.difference);
            twDifference.setText(R.string.difference_lag);

            twDifferenceResult = findViewById(R.id.difference_result);
            twDifferenceResult.setTextColor(getResources().getColor(R.color.red));
            twDifferenceResult.setText(String.valueOf(difference));
        }else if (requiredAmount < presentSale) {
            twDifference = findViewById(R.id.difference);
            twDifference.setText(R.string.difference_advance);

            twDifferenceResult = findViewById(R.id.difference_result);
            twDifferenceResult.setTextColor(getResources().getColor(R.color.green));
            twDifferenceResult.setText(String.valueOf(difference));
        }else {
            twDifference = findViewById(R.id.difference);
            twDifference.setText(R.string.difference_null);

            twDifferenceResult = findViewById(R.id.difference_result);
            String massage = getString(R.string.congratulations);
            twDifferenceResult.setText(massage);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("presentSaleBundle", presentSale);
        savedInstanceState.putDouble("totalSaleBundle", totalSale);
        savedInstanceState.putDouble("salesPerDay", salesPerDay);
        savedInstanceState.putDouble("requiredAmount", requiredAmount);
        savedInstanceState.putDouble("difference", difference);
    }
}
// Done:
// DecimalFormat
// Finish DailyResultActivity. difference tw and requiredBoost tw
// Change colors to red\green
// 18.10.2018 kill cityChooser 18.10.2018
// 19.10.2018 do Bundle 21.10.2018