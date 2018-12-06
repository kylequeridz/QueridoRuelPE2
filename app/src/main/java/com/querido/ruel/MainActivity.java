package com.querido.ruel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    EditText eStudent, eSection,eGender;
    TextView tMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eStudent = findViewById(R.id.etStudent);
        eSection = findViewById(R.id.etSection);
        eGender = findViewById(R.id.etGender);
        tMsg = findViewById(R.id.tvMsg);
    }

    public void saveInternal (View v){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data2.txt", Context.MODE_PRIVATE);
            String name2 = eStudent.getText().toString();
            String name3 = eSection.getText().toString();
            String name4 = eGender.getText().toString();
            fos.write(name2.getBytes());
            fos.write(name3.getBytes());
            fos.write(name4.getBytes());
            Toast.makeText(this,"Data saved!",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error writing data", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayInternal (View v){
        try {
            FileInputStream fin = openFileInput("data2.txt");
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c=fin.read()) != -1){
                buffer.append((char)c);
            }
            String message = "Information:" + buffer;
            tMsg.setText(message);
        } catch (Exception e) {
            Toast.makeText(this,"Error reading", Toast.LENGTH_LONG).show();
        }
    }
}