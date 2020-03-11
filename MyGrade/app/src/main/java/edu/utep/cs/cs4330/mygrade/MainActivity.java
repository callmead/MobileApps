package edu.utep.cs.cs4330.mygrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtUID, txtPIN;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lblUID = findViewById(R.id.lblUID);
        TextView lblPIN = findViewById(R.id.lblPIN);
        EditText txtUID = findViewById(R.id.txtUID);
        EditText txtPIN = findViewById(R.id.txtPIN);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this::submitClicked);
    }

    public void submitClicked(View view) {
        Toast.makeText(this, "Verifying user name and password!", Toast.LENGTH_SHORT).show();

        //Calling the activity
        //startActivity(new Intent(this, GradeActivity.class));
        Intent i = new Intent("edu.utep.cs.cs4330.GRADE");
        i.putExtra("userID", txtUID.getText());
        i.putExtra("userPIN", txtPIN.getText());
        startActivity(i);
    }
}