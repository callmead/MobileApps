package edu.utep.cs.cs4330.mygrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {
    private TextView lblData;
    private String userID, userPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        lblData = findViewById(R.id.lblData);

        Intent i = getIntent();
        userID = i.getStringExtra("userID");
        userPIN = i.getStringExtra("userPIN");
        Grade g = new Grade();

        WebClient web = new WebClient(new WebClient.GradeListener() {

            /** Called when a requested grade is received. */
            public void onGrade(String date, Grade grade) {
                runOnUiThread(() -> {
                    /* display grade */
                    lblData.setText("");
                });
            }

            /** Called when an error is encountered. */
            public void onError(String msg) {
                runOnUiThread(() -> {
                    /* inform error */
                    lblData.setText("Error occurred!");
                });
            }
        });

        new Thread(() -> web.query(userID, userPIN)).start();
    }
}
