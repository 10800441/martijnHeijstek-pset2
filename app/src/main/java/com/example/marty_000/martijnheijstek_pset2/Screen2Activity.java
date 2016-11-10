package com.example.marty_000.martijnheijstek_pset2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import java.io.IOException;
import java.util.Random;

public class Screen2Activity extends Activity {
    TextView remainingWords;
    TextView typeMessage;
    Button nextButton;
    EditText editText;
    InputStream is;
    private Story newStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

         // Make a new story or get a saved state
        if(savedInstanceState == null){
            try {
                newStory = createNewStory();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Problems: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else{
            newStory = (Story) savedInstanceState.getSerializable("newStory");
        }

        // Use the story to show the user which type of word is required
        remainingWords = (TextView) findViewById(R.id.boxMessage);
        typeMessage = (TextView) findViewById(R.id.typeMessage);
        nextButton = (Button) findViewById(R.id.nextButton);
        editText = (EditText) findViewById(R.id.editText);

        String hintText = newStory.getPlaceholderRemainingCount() + " words left";
        String typeText = "Please type a/an " + newStory.getNextPlaceholder();
        remainingWords.setText(hintText);
        typeMessage.setText(typeText);
        editText.setHint(newStory.getNextPlaceholder());
    }

    public Story createNewStory() throws IOException {

        StringBuffer buf = new StringBuffer();
        Random random = new Random();
        int fileIndex = random.nextInt(4);
       if(fileIndex == 0) {
           is = this.getResources().openRawResource(R.raw.madlib0_simple);
       } if(fileIndex == 1) {
            is = this.getResources().openRawResource(R.raw.madlib1_tarzan);
        } if(fileIndex == 2) {
            is = this.getResources().openRawResource(R.raw.madlib2_university);
        }if(fileIndex == 3) {
            is = this.getResources().openRawResource(R.raw.madlib3_clothes);
        }if(fileIndex == 4) {
            is = this.getResources().openRawResource(R.raw.madlib4_dance);
        }
        // Read the stream with the "Story" class
        try {
            if (is != null) {
                newStory = new Story(is);
            }
        } finally {
            try {
                is.close();
            } catch (Throwable ignore) {
            }
        }
        return newStory;
    }

    public void nextButtonClicked(View view) {

        String userInput = editText.getText().toString();
        // the user cannot enter an empty string
        if (userInput.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a word",
                    Toast.LENGTH_SHORT).show();
        } else {
            newStory.fillInPlaceholder(userInput);
            editText.getText().clear();

            if (newStory.isFilledIn()) {
                Intent intent = new Intent(this, Screen3Activity.class);
                intent.putExtra("newStory", newStory.toString());
                startActivity(intent);
                finish();
            } else {
                
                String hintText = newStory.getPlaceholderRemainingCount() + " words left";
                if (newStory.getPlaceholderRemainingCount() == 1) {
                    String string = "Finish";
                    nextButton.setText(string);
                } else {
                    Toast.makeText(getApplicationContext(), "Great! Keep going!",
                            Toast.LENGTH_SHORT).show();
                }
                String typeText = "Please type a/an " + newStory.getNextPlaceholder();
                remainingWords.setText(hintText);
                editText.setHint(newStory.getNextPlaceholder());
                typeMessage.setText(typeText);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("newStory", newStory);
    }
}