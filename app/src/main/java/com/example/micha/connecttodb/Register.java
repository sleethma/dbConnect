package com.example.micha.connecttodb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.net.URLEncoder;

public class Register extends AppCompatActivity {

    EditText nameEntry, countryEntry, passwordEntry, contactEntry;
    String name, password, contact, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEntry = (EditText) findViewById(R.id.et_name);
        countryEntry = (EditText) findViewById(R.id.et_country);
        passwordEntry = (EditText) findViewById(R.id.et_password);
        contactEntry = (EditText) findViewById(R.id.et_contact);
    }
    //adds user info into db through bkgd task
    public void registerUser(View view){
        name = nameEntry.getText().toString();
        password = passwordEntry.getText().toString();
        contact = contactEntry.getText().toString();
        country = countryEntry.getText().toString();
        String registerBackgroundTask = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(registerBackgroundTask, name, password, contact, country);
        finish();
    }
    //gets JSON from server PHP call
    public void getJSON(View view){
        String getJsonBackgroundTask = "getJSON";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(getJsonBackgroundTask);
    }

}
