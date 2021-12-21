package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class randomToto extends AppCompatActivity implements SensorEventListener {

    SmsUtils smsUtils = new SmsUtils();
    ErrorUtils errorUtils = new ErrorUtils();
    private static final float SHAKE_THRESHOLD = 8.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private SensorManager mSensorMgr;
    EditText tippOcount;
    private String message = "TO#";
    boolean state = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_toto);
        // Get a sensor manager to listen for shakes
        mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Listen for shakes
        Sensor accelerometer = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorMgr.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        tippOcount = findViewById(R.id.tippOszlopEditText);
        state = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (state) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                long curTime = System.currentTimeMillis();
                if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];
                    double acceleration = Math.sqrt(Math.pow(x, 2) +
                            Math.pow(y, 2) +
                            Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                    if (acceleration > SHAKE_THRESHOLD) {
                        mLastShakeTime = curTime;
                        // Get instance of Vibrator from current Context
                        Vibrator v = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        }
                        // Vibrate for 400 milliseconds
                        v.vibrate(400);
                        if (tippOcount.getText().toString().equals("") || Integer.parseInt(tippOcount.getText().toString()) < 2) {
                            errorUtils.errorAlert(this, "HIBA! Adjon meg legalább 2 tipposzlopot!");

                        } else {
                            message = message + tippOcount.getText().toString();
                            Log.i("SMS", message);
                            smsUtils.sendSMS(getApplicationContext(),message);
                            state = false;
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);

                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onClickBack(View view) {
        state = false;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}