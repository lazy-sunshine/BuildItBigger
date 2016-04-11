package com.example.dell.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MyJokeDisplay extends AppCompatActivity {
TextView mjoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_joke_display);
        String z=getIntent().getStringExtra("joke");
        mjoke= (TextView) findViewById(R.id.joke);
        mjoke.setText(z);
    }
}
