package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLeiras = (Button) findViewById(R.id.buttonLeiras);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonLeiras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://bet.szerencsejatek.hu/utmutato/sms-lotto-fogadas/sms-lotto-fogadas-reszletes-utmutato"));
                startActivity(intent);
            }
        });
    }

    public void onClickTipper(View view) {
        Intent intent = new Intent(this, tippMenuActivity.class);
        startActivityForResult(intent, 1);
//        this.finish();
    }

    public void onClickDetails(View view) {
        Intent intent = new Intent(this, UserDatas.class);
        startActivityForResult(intent, 4);
//        this.finish();
    }

    public void onClickWebcontent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://bet.szerencsejatek.hu/jatekok/toto"));
        startActivity(intent);
    }

}