package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent i = getIntent();
        if (i.hasExtra(Intent.EXTRA_TEXT)) {
            String weather = i.getStringExtra(Intent.EXTRA_TEXT);
            TextView tvWeatherDetails = (TextView) findViewById(R.id.tv_weather_details);
            tvWeatherDetails.setText(weather);
        }
    }
}