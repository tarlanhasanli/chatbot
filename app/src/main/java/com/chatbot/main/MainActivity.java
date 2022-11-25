package com.chatbot.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Gyroscope gyroscope;
    private Accelerometer accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscope = new Gyroscope(this);
        accelerometer = new Accelerometer(this);

        // on rotation method of gyroscope
        gyroscope.setListener((rx, ry, rz) -> {
            // set sensor data
            TextView tv = (TextView) findViewById(R.id.gyro_sensor);
            tv.setText(getSensorValue("Gyroscope", rx, ry, rz));
        });

        accelerometer.setListener(new Accelerometer.Listener() {
            //on translation method of accelerometer
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                // set sensor data
                TextView tv = (TextView) findViewById(R.id.accelerate_sensor);
                tv.setText(getSensorValue("Accelerometer", tx, ty, tz));
            }
        });
    }

    // create on resume method
    @Override
    protected void onResume() {
        super.onResume();

        // this will send notification to
        gyroscope.register();
        accelerometer.register();
    }

    // create on pause method
    @Override
    protected void onPause() {
        super.onPause();

        // this will send notification in
        gyroscope.unregister();
        accelerometer.unregister();
    }

    private String getSensorValue(String sensorName, float x, float y, float z) {
        return sensorName + ": X: " + x + "; Y: " + y + "; Z: " + z + ";";
    }
}