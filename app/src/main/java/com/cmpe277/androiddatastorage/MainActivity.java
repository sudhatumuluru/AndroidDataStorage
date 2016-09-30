package com.cmpe277.androiddatastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextView) findViewById(R.id.input);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            InputStream input_Text = openFileInput(PreferencesActivity.STORE_PREFERENCES);
            if(input_Text != null) {
                InputStreamReader temp=new InputStreamReader(input_Text);
                BufferedReader br=new BufferedReader(temp);
                String str;
                StringBuilder buf=new StringBuilder();
                while((str=br.readLine())!=null) {
                    buf.append(str +"\n");
                }
                input_Text.close();
                input.setText(buf.toString());
                input.setVisibility(View.VISIBLE);
            } else {
                input.setVisibility(View.INVISIBLE);
            }
        } catch(Exception e) {
            e.printStackTrace();
            input.setVisibility(View.INVISIBLE);
        }
    }

    public void onSelectPreferences(View v) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    public void onSelectSqlite(View v) {
        Intent intent = new Intent(this, SQLiteActivity.class);
        startActivity(intent);
    }

    public void closeApp(View v) {
        MainActivity.this.finish();
        System.exit(0);
    }
}
