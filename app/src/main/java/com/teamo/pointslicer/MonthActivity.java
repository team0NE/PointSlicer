package com.teamo.pointslicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class MonthActivity extends Activity{
    private double presentSale;
    private double totalSale;
    private double percent;
    private String cityChoice;
    private String fullAddress;
    private String message;

    TextView twFinalSalesAmountResult;
    TextView twFinalRequiredAmountResult;
    TextView twFinalPercentResult;


    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_result_layout);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        Intent receiveIntent = this.getIntent();
        presentSale = receiveIntent.getDoubleExtra("presentSale", presentSale);
        presentSale = Double.parseDouble(df.format(presentSale));
        totalSale = receiveIntent.getDoubleExtra("totalSale", totalSale);
        totalSale = Double.parseDouble(df.format(totalSale));
        cityChoice = receiveIntent.getStringExtra(EXTRA_MESSAGE);

        percent = Double.parseDouble(df.format((presentSale/totalSale) * 100));

        twFinalSalesAmountResult = findViewById(R.id.final_month_amount_result);
        twFinalSalesAmountResult.setText(String.valueOf(presentSale));

        twFinalRequiredAmountResult = findViewById(R.id.final_required_sales_amount_result);
        twFinalRequiredAmountResult.setText(String.valueOf(totalSale));

        twFinalPercentResult = findViewById(R.id.final_percent_result);
        twFinalPercentResult.setText(String.valueOf(percent));

        fullAddress = setFullAddress(cityChoice);

        message = fullAddress + " Факт: " + presentSale + ". " + "План: " + totalSale + ". " + "Процент виконання: " + percent + "%";

        TextView massageTest = findViewById(R.id.massage_test);
        massageTest.setText(message);
    }

    public String setFullAddress(String cityChoice) {
        switch (cityChoice) {
            case "Львів": fullAddress = "м. Львів, вул. Городоцька, 302";
                break;
            case "Одеса": fullAddress = "м. Одеса, просп. Жукова, 99";
                break;
            case "Херсон": fullAddress = "м. Херсон, Бериславське шосе, 17";
                break;
            case "Хмильницький": fullAddress = "м. Хмильницький, вул. Зарічанська, 114";
                break;
        }
        return fullAddress;
    }

    public void sendMSG(View view) {
        Intent viberIntent = new Intent(Intent.ACTION_SEND);
        viberIntent.setType("text/plain");
        viberIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(viberIntent);
    }
}
// TODO: 19.10.2018 do Bundle
// Done:
// 14.10.2018 tw with a result string, to check the message
//05.10.2018 clipboard method send to viber 18.10.2018