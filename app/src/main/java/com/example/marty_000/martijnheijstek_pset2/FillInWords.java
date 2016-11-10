package com.example.marty_000.martijnheijstek_pset2;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by marty_000 on 8-11-2016.
*/

public class FillInWords extends Activity {

    Button nextButton;
    TextView remainingWords;
    EditText editText;
    InputStream stream;
    private Story newStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
            try {
                playWithRawFiles();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Problems: " + e.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        }

        public void playWithRawFiles() throws IOException {
            String str = "";
            StringBuffer buf = new StringBuffer();
            InputStream is = this.getResources().openRawResource(R.raw.madlib0_simple);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                if (is != null) {
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n" );
                    }
                }
            } finally {
                try { is.close(); } catch (Throwable ignore) {}
            }
            Toast.makeText(getBaseContext(), buf.toString(), Toast.LENGTH_LONG).show();
        }
    public void nextButtonClicked(View view) {
        System.out.print("ees");
    }
}

