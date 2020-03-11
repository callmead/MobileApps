package edu.utep.cs.cs4330.timer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView timeDisplay;
    private Button startButton;
    private Button stopButton;
    private TimerModel timerModel;
    private String tag = "Lifecycle Step";
    private String timeValue = "";
    private String timeValue2="";

    @Override
    //updates July 17
    protected void onStart() {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }
    protected void onResume() {
        super.onResume();
        Log.d(tag, "In the onResume() event");
    }
    protected void onPause() {
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }
    protected void onStop() {
        super.onStop();
        Log.d(tag, "In the onStop() event");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }
    //updates July 17
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        if (savedInstanceState != null) {
            timeDisplay.setText(timeValue);
        }*/
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("StateInfo","Landscape");
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("StateInfo","Portrait");
        }

        setContentView(R.layout.activity_main);
        Log.d(tag, "In the onCreate() event");

        timeDisplay = findViewById(R.id.timeDisplay);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this::startClicked);

        stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(this::stopClicked);

        timerModel = new TimerModel();
        timerModel.startTimer();
        timeDisplay.setText("0:00:00");
    }
    //updates July 17
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("time", timerModel.getStatingTime());
    }
    //updates July 17
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        timerModel = new TimerModel(savedInstanceState.getLong("time"));
        displyTime();
    }

    public void startClicked(View view) {
        Toast.makeText(this, "Start tapped!", Toast.LENGTH_SHORT).show();
        timerModel.startTimer();
        new Thread(() -> {
            while (timerModel.isRunning()) {
                this.runOnUiThread(this::displyTime);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {}
            }
        }).start();

        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    private void displyTime() {
        long sec = timerModel.elapsedTime() / 1000;
        long min = sec / 60; sec %= 60;
        long hour = min / 60; min %= 60;
        timeDisplay.setText(String.format("%d:%02d:%02d", hour, min, sec));
        timeValue = String.format("%d:%02d:%02d", hour, min, sec);
    }

    private void stopClicked(View view) {
        Toast.makeText(this, "Stop tapped!", Toast.LENGTH_SHORT).show();
        setTimeDisplay(timeDisplay);
        timerModel.stopTimer();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    public TextView getTimeDisplay() {
        return timeDisplay;
    }

    public void setTimeDisplay(TextView timeDisplay) {
        this.timeDisplay = timeDisplay;
    }
}