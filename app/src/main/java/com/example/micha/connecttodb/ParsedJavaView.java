package com.example.micha.connecttodb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ParsedJavaView extends AppCompatActivity {

    String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parsed_json_view);
        jsonString = getIntent().getExtras().getString("jsonToParseKey");
    }
}
