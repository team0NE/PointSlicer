package com.teamo.pointslicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;


public class MonthActivity extends Activity{
    private double presentSale;
    private double totalSale;
    private double percent;
    private String cityChoice;
    private String fullAddress;
    private String monthReport;

    TextView twFinalSalesAmountResult;
    TextView twFinalRequiredAmountResult;
    TextView twFinalPercentResult;


    public static final String EXTRA_CITY = "cityChoice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_result_layout);
        if(savedInstanceState != null) {
            presentSale = savedInstanceState.getDouble("presentSale", presentSale);
            totalSale = savedInstanceState.getDouble("totalSale", totalSale);
            percent = savedInstanceState.getDouble("percent", percent);
        }

        Intent receiveIntent = this.getIntent();
        presentSale = receiveIntent.getDoubleExtra("presentSale", presentSale);
        String presentSaleStr = String.format(Locale.FRANCE, "%.2f", presentSale);
        totalSale = receiveIntent.getDoubleExtra("totalSale", totalSale);
        String totalSaleStr = String.format(Locale.FRANCE, "%.2f", totalSale);
        cityChoice = receiveIntent.getStringExtra(EXTRA_CITY);

        percent = (presentSale/totalSale);
        String percentStr = String.format(Locale.FRANCE,"%.2f", percent) + "%";

        /*
        salesPerDay = (totalSale/amountOfDays);
        String salesPerDayStr = String.format("%.2f", salesPerDay);
        salesPerDay = Double.parseDouble(salesPerDayStr);
        */

        twFinalSalesAmountResult = findViewById(R.id.final_month_amount_result);
        twFinalSalesAmountResult.setText(presentSaleStr);

        twFinalRequiredAmountResult = findViewById(R.id.final_required_sales_amount_result);
        twFinalRequiredAmountResult.setText(totalSaleStr);

        twFinalPercentResult = findViewById(R.id.final_percent_result);
        twFinalPercentResult.setText(String.valueOf(percentStr));

        fullAddress = setFullAddress(cityChoice);

        monthReport = fullAddress + " Факт: " + presentSaleStr + ". " + "План: " + totalSaleStr + ". " + "Процент виконання: " + percentStr;

        TextView massageTest = findViewById(R.id.massage_test);
        massageTest.setText(monthReport);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("presentSaleBundle", presentSale);
        savedInstanceState.putDouble("totalSaleBundle", totalSale);
        savedInstanceState.putDouble("percent", percent);
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
        viberIntent.putExtra(Intent.EXTRA_TEXT, monthReport);
        startActivity(viberIntent);
    }
}
// Done:
// 14.10.2018 tw with a result string, to check the monthReport
// 05.10.2018 clipboard method send to viber 18.10.2018
// 19.10.2018 do Bundle 21.10.2018