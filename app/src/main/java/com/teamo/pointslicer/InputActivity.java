package com.teamo.pointslicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;


public class InputActivity extends Activity{
    private EditText etPresentSale;
    private EditText etTotalSale;
    private Button mButtonPresent;
    private Button mButtonMonth;
    private Spinner mSpinner;

    private double presentSale;
    private double totalSale;
    private String cityChoice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        if(savedInstanceState != null) {
            presentSale = savedInstanceState.getDouble("presentSale", presentSale);
            totalSale = savedInstanceState.getDouble("totalSale", totalSale);
        }

        etPresentSale = findViewById(R.id.presentSalesAmount);
        etTotalSale = findViewById(R.id.totalSalesAmount);

        mButtonPresent = findViewById(R.id.calculate_date);
        mButtonPresent.setOnClickListener(new View.OnClickListener() {

            String presentSaleStr = etPresentSale.getText().toString();
            String totalSaleStr = etTotalSale.getText().toString();
            @Override
            public void onClick(View v) {
                if (etPresentSale.getText().toString().isEmpty()) {
                    etPresentSale.setError(getResources().getString(R.string.you_forgot_input_present));
                    return;
                }else if (etTotalSale.getText().toString().isEmpty()) {
                    etTotalSale.setError(getResources().getString(R.string.you_forgot_input_total));
                    return;
                }
                setInput();

                Intent intent = new Intent(InputActivity.this, DailyResultActivity.class);
                intent.putExtra("presentSale", presentSale);
                intent.putExtra("totalSale", totalSale);
                startActivity(intent);
            }
        });

        mButtonMonth = findViewById(R.id.calculate_month);
        mButtonMonth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etPresentSale.getText().toString().isEmpty()) {
                    etPresentSale.setError(getResources().getString(R.string.you_forgot_input_present));
                    return;
                }else if (etTotalSale.getText().toString().isEmpty()) {
                    etTotalSale.setError(getResources().getString(R.string.you_forgot_input_total));
                    return;
                }
                setInput();

                Intent intent = new Intent(InputActivity.this, MonthActivity.class);
                intent.putExtra("presentSale", presentSale);
                intent.putExtra("totalSale", totalSale);
                intent.putExtra(MonthActivity.EXTRA_CITY, cityChoice);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("presentSale", presentSale);
        savedInstanceState.putDouble("totalSale", totalSale);
    }

    public void setInput() {
        /*DecimalFormat df = new DecimalFormat("#######0,##");
        df.setRoundingMode(RoundingMode.CEILING);*/

        presentSale = Double.parseDouble(String.format(Locale.FRANCE,etPresentSale.getText().toString()));
        //presentSale = Double.parseDouble(df.format(presentSale));      //Math.round(presentSale * 100d)/100d; <-- (double)Math.round(value * 100000d) / 100000d
        totalSale = Double.parseDouble(String.format(Locale.FRANCE, etTotalSale.getText().toString()));// over the DecimalFormat
        //totalSale = Double.parseDouble(df.format(totalSale));
        mSpinner = findViewById(R.id.city_chooser);
        cityChoice = String.valueOf(mSpinner.getSelectedItem());
    }
}
// TODO: 30.10.2018 notifications rollUp. hint, floatingLabels
// Done:
// result activity
// finished input fields
// finished Intent
// second button for MonthCalc
// 19.10.2018 do Bundle 21.10.2018
// 18.10.2018 check fialds for data 22.10.2018