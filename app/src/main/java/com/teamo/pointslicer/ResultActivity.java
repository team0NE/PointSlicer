package com.teamo.pointslicer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class ResultActivity extends AppCompatActivity {
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        mSpinner = findViewById(R.id.city_chooser);
    }
}
