package com.example.marty_000.martijnheijstek_pset2;

/* Mad Libs
 * 11-11-2016
 * Martijn Heijstek, 10800441
 * Welcome screen with instuctions
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Go to the page where words are filled in
    public void initStory(View view) {
         startActivity(new Intent (this, Screen2Activity.class));
         finish();
    }


}
