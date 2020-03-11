package edu.utep.cs.cs4330.tuitioncalculation;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView lblTop;
    private EditText txtET;
    private TextView lblBottom;
    private RadioGroup radioGroup3;
    private RadioButton rdoGraduate;
    private RadioButton rdoUnderraduate;
    private TextView lblTuition;
    private TextView lblFee;
    private TextView lblTotal;
    private TuitionCalculator calculator;
    private String studentType="Undergraduate";
    private String et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblTop = findViewById(R.id.lblTop);
        txtET = findViewById(R.id.txtET);
        //txtET.setText("0");
        lblBottom = findViewById(R.id.lblBottom);
        radioGroup3 = findViewById(R.id.radioGroup3);
        rdoGraduate = findViewById(R.id.rdoGraduate);
        rdoGraduate.setOnClickListener(this::onRadioButtonClicked);
        rdoUnderraduate = findViewById(R.id.rdoUndergraduate);
        rdoGraduate.setChecked(true);//setting by default.
        rdoUnderraduate.setOnClickListener(this::onRadioButtonClicked);
        lblTuition = findViewById(R.id.lblTuition);
        lblFee = findViewById(R.id.lblFee);
        lblTotal = findViewById(R.id.lblTotal);
        calculator = new TuitionCalculator();

        txtET.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et = txtET.getText().toString();
                if (et.matches("")) {
                    return;//if txtER is empty return
                }else {
                    calculator.setCreditHours(studentType, Integer.parseInt(s.toString()));
                    displayTutition();
                }
            }
            public void afterTextChanged(Editable s) {}
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdoGraduate:
                if (checked)
                    studentType = "Graduate";
                    et = txtET.getText().toString();
                    calculator.setCreditHours(studentType, Integer.parseInt(et));
                    displayTutition();
                    break;
            case R.id.rdoUndergraduate:
                if (checked)
                    studentType = "Undergraduate";
                    et = txtET.getText().toString();
                    calculator.setCreditHours(studentType, Integer.parseInt(et));
                    displayTutition();
                break;
        }
    }
    public void displayTutition(){
        lblFee.setText("Fee: "+Integer.toString(calculator.fee));
        lblTuition.setText("Tuition: "+Integer.toString(calculator.tuition_fee));
        lblTotal.setText("Total: "+Integer.toString(calculator.totalFee));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ET", txtET.getText().toString());
        if(rdoUnderraduate.isChecked()){outState.putString("rdo", "Undergraduate");}
        else if (rdoGraduate.isChecked()){outState.putString("rdo", "Graduate");}else{}
        outState.putString("Tuition", lblTuition.getText().toString());
        outState.putString("Fee", lblFee.getText().toString());
        outState.putString("Total", lblTotal.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtET.setText(savedInstanceState.getString("ET"));
        if(savedInstanceState.getString("rdo").equals("Graduate")){rdoGraduate.setChecked(true);}
        else if(savedInstanceState.getString("rdo").equals("Undergraduate")){rdoUnderraduate.setChecked(true);}else{}
        lblTuition.setText(savedInstanceState.getString("Tuition"));
        lblFee.setText(savedInstanceState.getString("Fee"));
        lblTotal.setText(savedInstanceState.getString("Total"));
    }
}
