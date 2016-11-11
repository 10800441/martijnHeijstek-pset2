package com.example.marty_000.martijnheijstek_pset2;
/* Mad Libs
 * 11-11-2016
 * Martijn Heijstek, 10800441
 * Screen where the user has to fill in the missing words
 */
import android.app.Activity;
import android.content.Intent;
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
    private TextView remainingWords;
    private TextView typeMessage;
    private Button nextButton;
    private EditText editText;
    private InputStream is;
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

    // Choose a new story at random
    public Story createNewStory() throws IOException {
        Random random = new Random();
        int fileIndex = random.nextInt(4);

        // path = ...\martijnHeijstek-pset2\app\src\main\res\raw\"file name"
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
        newStory = new Story(is);

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
                updateScreen();
            }
        }
    }

    private void updateScreen() {
        String hintText = newStory.getPlaceholderRemainingCount() + " words left";
        if (newStory.getPlaceholderRemainingCount() == 1) {
            String string = "Finish";
            nextButton.setText(string);
        } else {
            Toast.makeText(getApplicationContext(), "Great! Keep going!",
                    Toast.LENGTH_SHORT).show();
        }
        // Indicate the type of word the user has to input
        String typeText = "Please type a/an " + newStory.getNextPlaceholder();
        remainingWords.setText(hintText);
        editText.setHint(newStory.getNextPlaceholder());
        typeMessage.setText(typeText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("newStory", newStory);
    }
}