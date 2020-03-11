package edu.utep.cs.cs4330.fitnesstracker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sm;
    private TextView lblCounter;
    boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCounter = (TextView)findViewById(R.id.lblCounter);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        activityRunning=true;
        Sensor countSensor = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sm.registerListener(this, countSensor, sm.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Something went wrong while trying to get sensor.", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning=false;
        sm.unregisterListener(this);
    }

    //Sensor
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(activityRunning){
            int steps = (int)(sensorEvent.values[0]);
            lblCounter.setText(Integer.toString(steps));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    //SensorEvents.......................................
}
