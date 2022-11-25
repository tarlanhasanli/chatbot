package com.chatbot.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Gyroscope gyroscope;
    private Accelerometer accelerometer;
    private MagneticField magneticField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscope = new Gyroscope(this);
        accelerometer = new Accelerometer(this);
        magneticField = new MagneticField(this);

        // on rotation method of gyroscope
        // on rotation method of gyroscope
        gyroscope.setListener((rx, ry, rz) -> {
            // set the color green if the device rotates on positive z axis
            TextView tv = (TextView) findViewById(R.id.accelerate_sensor);
            tv.setText(getSensorValue("Gyroscope", rx, ry, rz));
        });

        //on translation method of accelerometer
        accelerometer.setListener((tx, ty, tz) -> {
            // set sensor data
            TextView tv = (TextView) findViewById(R.id.accelerate_sensor);
            tv.setText(getSensorValue("Accelerometer", tx, ty, tz));
        });

        magneticField.setListener((mx, my, mz) -> {
            // set sensor data
            TextView tv = (TextView) findViewById(R.id.magnetic_field);
            tv.setText(getSensorValue("Magnetic Field", mx, my, mz));
        });
    }

    // create on resume method
    @Override
    protected void onResume() {
        super.onResume();

        // this will send notification to
        gyroscope.register();
        accelerometer.register();
        magneticField.register();
    }

    // create on pause method
    @Override
    protected void onPause() {
        super.onPause();

        // this will send notification in
        gyroscope.unregister();
        accelerometer.unregister();
        magneticField.unregister();
    }

    private String getSensorValue(String sensorName, float x, float y, float z) {
        return sensorName + ": X: " + x + "; Y: " + y + "; Z: " + z + ";";
    }
}