package com.example.marty_000.martijnheijstek_pset2;

/* Mad Libs
 * 11-11-2016
 * Martijn Heijstek, 10800441
 * Screen where the completed story is shown
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Screen3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        Intent intent = getIntent();
        String newStory = intent.getExtras().getString("newStory");

        //Display the story in a textview
        TextView madlibStory = (TextView) findViewById(R.id.storyText);
        madlibStory.setText(newStory);
    }
    // Redirects to the fill in page
    public void otherStory(View view){
        startActivity(new Intent (this, Screen2Activity.class));
        finish();
    }
}
