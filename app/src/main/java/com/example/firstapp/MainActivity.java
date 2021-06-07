package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView xView;
    private TextView yView;
    private TextView zView;
    private boolean buttonState = true;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView,BlankFragment.class,null).commit();
        }

        xView = findViewById(R.id.textView1);
        yView = findViewById(R.id.textView2);
        zView = findViewById(R.id.textView3);


    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        buttonState = ((BlankFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView)).getButtonState();

       if(buttonState){
           if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

               String xVal = dataToString(sensorEvent.values[0]);
               String yVal = dataToString(sensorEvent.values[1]);
               String zVal = dataToString(sensorEvent.values[2]);

               xView.setText(xVal);
               yView.setText(yVal);
               zView.setText(zVal);

               Matrix matrix = new Matrix();

              float xRotation = image.getRotationX() + Float.valueOf(yVal)/20;
              float yRotation = image.getRotationY() + Float.valueOf(xVal)/20;
              int limit = 30;

              if(xRotation < limit && xRotation > -limit){
                  image.setRotationX(xRotation);
              }

              if(yRotation < limit && yRotation > -limit){
                   image.setRotationY(yRotation);
              }

           }
       }
    }

    private String dataToString(float raw){
        int a = Math.round(raw);
        return String.valueOf(a);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}