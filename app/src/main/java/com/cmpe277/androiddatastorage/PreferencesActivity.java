package com.cmpe277.androiddatastorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreferencesActivity extends AppCompatActivity {

    private EditText bookName;
    private EditText authorName;
    private EditText desc;
    private int counter = 0;
    private SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
    public final static String STORE_PREFERENCES= "assignment4_pref.txt";
    public final static String BOOK_PREFERCENS = "assignment4_books.text"; //File to save book title
    private final static String COUNTER = "COUNTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        bookName = (EditText) findViewById(R.id.book_name_text);
        authorName = (EditText) findViewById(R.id.book_name_text);
        desc = (EditText) findViewById(R.id.desc_text);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter = sharedPrefs.getInt(COUNTER, 0);
    }

    public void onSavePreferences(View v) {
        String name = bookName.getText().toString();
        String author = authorName.getText().toString();
        String description = desc.getText().toString();
        if ((name != null) && (author != null) && (description != null)) {
            try {
                counter += 1;
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(COUNTER, counter);
                editor.commit();
                //Share preference
                OutputStreamWriter out=new OutputStreamWriter(openFileOutput(STORE_PREFERENCES, MODE_APPEND));
                String message = "\nSaved Preference " + counter + ", " + s.format(new Date());
                out.write(message);
                out.close();
                //Book preferences
                OutputStreamWriter out1 =new OutputStreamWriter(openFileOutput(BOOK_PREFERCENS, MODE_APPEND));
                String book = "\n" + name + ", " + author + ". Desc: " + description;
                out1.write(book);
                out1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else{
            Toast.makeText(getApplicationContext(), "Please enter valid info.",Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void onCancel(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
