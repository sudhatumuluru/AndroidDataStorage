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
            InputStream in = openFileInput(PreferencesActivity.STORE_PREFERENCES);
            if(in != null) {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while((str=reader.readLine())!=null) {
                    buf.append(str +"\n");
                }
                in.close();
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
