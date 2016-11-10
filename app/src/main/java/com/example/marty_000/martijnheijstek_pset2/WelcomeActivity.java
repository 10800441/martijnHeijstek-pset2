package com.example.marty_000.martijnheijstek_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
    }
    public void initStory(View view) {
        Intent a = new Intent (this, Screen2Activity.class);
        startActivity(a);
        finish();
    }


}
