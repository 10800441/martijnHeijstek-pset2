package com.example.marty_000.martijnheijstek_pset2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;

import java.io.IOException;
import java.util.Random;

import static android.R.attr.name;
import static android.R.id.message;
import static android.content.res.AssetManager.ACCESS_RANDOM;
import static com.example.marty_000.martijnheijstek_pset2.R.id.nextButton;

import com.example.marty_000.martijnheijstek_pset2.Story;

public class Screen2Activity extends Activity {
    SharedPreferences prefs;

    TextView remainingWords;
    Button nextButton;
    EditText editText;
    InputStream stream;

    private Story newStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);

        if (savedInstanceState == null) {
            AssetManager assetManager = getAssets();
            try {
                stream = assetManager.open("textFiles/" + ACCESS_RANDOM);
                newStory = new Story(stream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } // Get a saved story
        else {
            newStory = (Story) savedInstanceState.getSerializable("newStory");
            String text;
            if (newStory.getPlaceholderRemainingCount() == 1) {
                text = newStory.getPlaceholderRemainingCount() + " word left";
                String string = "Finish";
                nextButton.setText(string);
            } else {
                text = newStory.getPlaceholderRemainingCount() + " words left";
            }

            remainingWords.setText(text);
            editText.setHint(newStory.getNextPlaceholder());
        }

    }
    public void nextButtonClicked(View view) {
        newStory.fillInPlaceholder(editText.getText().toString());
        editText.getText().clear();



    }
}
