package com.example.marty_000.martijnheijstek_pset2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Screen3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        SharedPreferences prefs = this.getSharedPreferences("setttings", this.MODE_PRIVATE);
        String name = prefs.getString("name", "");

        //TextView welcomeTV = (TextView) findViewById(R.id.TextView2);
        //welcomeTV.setText("welcome " + name + "! \n Please enter something");

    }
    public void toNext(View view){

    }
}
