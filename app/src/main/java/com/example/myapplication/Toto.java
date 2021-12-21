package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Toto extends AppCompatActivity {

    SmsUtils smsUtils = new SmsUtils();
    ErrorUtils errorUtils = new ErrorUtils();
    private boolean isError = false;

    private final int[] btns = {
            R.id.rB11, R.id.rB1X, R.id.rB12,
            R.id.rB21, R.id.rB2X, R.id.rB22,
            R.id.rB31, R.id.rB3X, R.id.rB32,
            R.id.rB41, R.id.rB4X, R.id.rB42,
            R.id.rB51, R.id.rB5X, R.id.rB52,
            R.id.rB61, R.id.rB6X, R.id.rB62,
            R.id.rB71, R.id.rB7X, R.id.rB72,
            R.id.rB81, R.id.rB8X, R.id.rB82,
            R.id.rB91, R.id.rB9X, R.id.rB92,
            R.id.rB101, R.id.rB10X, R.id.rB102,
            R.id.rB111, R.id.rB11X, R.id.rB112,
            R.id.rB121, R.id.rB12X, R.id.rB122,
            R.id.rB131, R.id.rB13X, R.id.rB132,
            R.id.rB141, R.id.rB14X, R.id.rB142
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto);
    }

    public void onClickSmsSend(View view) {
        StringBuilder sb = new StringBuilder("TK#");
        for (int i = 0; i < btns.length; i = i + 3) {
            RadioButton radioButton = (RadioButton) findViewById(btns[i]);
            RadioButton radioButton2 = (RadioButton) findViewById(btns[i+1]);
            RadioButton radioButton3 = (RadioButton) findViewById(btns[i+2]);

            if (radioButton.isChecked()) {
                sb.append("1");
            }

            if (radioButton2.isChecked()) {
                sb.append("X");
            }

            if (radioButton3.isChecked()) {
                sb.append("2");
            }

            if ((!radioButton.isChecked())&&(!radioButton2.isChecked())&&(!radioButton3.isChecked())) {
                    isError = true;
                }
            else {
                sb.append("#");
            }
        }
        if (!isError){
            sb.deleteCharAt(sb.length() - 1);
            Log.i("MSG:", sb.toString());
            String message = sb.toString();
            smsUtils.sendSMS(getApplicationContext(), message);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            errorUtils.errorAlert(this, "HIBA! Jelöljön meg soronként legalább 1 mezőt!");
            isError = false;
        }
    }


    /*@Override
    public void onClick(View view) {
        for (int i = 1; i < 15; i++) {
            for (String elem : kimenet) {
                int id;
                String btnName = "rB" + i + elem;
                id = getResources().getIdentifier(
                        btnName,
                        "id",
                        Toto.this.getPackageName());
                RadioButton akt_button = (RadioButton) view.findViewById(R.id.rB11);
                if (akt_button.isChecked()) {
                    message.append(i).append(elem).append("#");
                }
            }

        }
        message.deleteCharAt(message.length() - 1);
        Log.i("MSG:", message.toString());
    }*/

}