package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserDatas extends AppCompatActivity {

    SmsUtils smsUtils = new SmsUtils();
    ErrorUtils errorUtils = new ErrorUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_datas);
    }

    public void onClickSaveData(View view) {
        EditText editName = (EditText) findViewById(R.id.editTextName);
        EditText editSzamlaSzam = (EditText) findViewById(R.id.editTextSzamlaszam);

        if (!(editSzamlaSzam.getText().toString().equals("") || editName.getText().toString().equals(""))) {
            if (editSzamlaSzam.getText().toString().length() == 16 || editSzamlaSzam.getText().toString().length() == 24) {
                String message = "UTAL*" + editName.getText().toString() + "*" + editSzamlaSzam.getText().toString() + "*";
                Log.i("DATA", message);
                smsUtils.sendSMS(getApplicationContext(), message);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                errorUtils.errorAlert(this, "Hibás számlaszám!");
            }
        } else {
            errorUtils.errorAlert(this, "HIBA! Töltse ki az adatokat!");
        }
    }


    public void onClickVissza(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 2);
    }
}