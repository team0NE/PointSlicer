package com.teamo.pointslicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.math.RoundingMode;
import java.text.DecimalFormat;


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

        etPresentSale = findViewById(R.id.presentSalesAmount);
        etTotalSale = findViewById(R.id.totalSalesAmount);

        mButtonPresent = findViewById(R.id.calculate_date);
        mButtonPresent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendInput();

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
                sendInput();

                Intent intent = new Intent(InputActivity.this, MonthActivity.class);
                intent.putExtra("presentSale", presentSale);
                intent.putExtra("totalSale", totalSale);
                intent.putExtra(MonthActivity.EXTRA_MESSAGE, cityChoice);
                startActivity(intent);
            }
        });


    }

    private void sendInput() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        presentSale = Double.parseDouble(etPresentSale.getText().toString());
        presentSale = Double.parseDouble(df.format(presentSale));      //Math.round(presentSale * 100d)/100d; <-- (double)Math.round(value * 100000d) / 100000d
        totalSale = Double.parseDouble(etTotalSale.getText().toString());// over the DecimalFormat
        totalSale = Double.parseDouble(df.format(totalSale));
        mSpinner = findViewById(R.id.city_chooser);
        cityChoice = String.valueOf(mSpinner.getSelectedItem());
    }
}
// TODO: 18.10.2018 check for Nullable with toast
// TODO: 19.10.2018 do Bundle
// Done:
// result activity
// finished input fields
// finished Intent
// second button for MonthCalc