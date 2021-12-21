package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tippMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp_menu);
    }

    public void onClickRandom(View view) {
        Intent intent = new Intent(this, randomToto.class);
        startActivityForResult(intent, 2);
    }

    public void onClickKeziTipp(View view) {
        Intent intent = new Intent(this, Toto.class);
        startActivityForResult(intent, 3);
    }
}