package com.example.matt.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Matt on 10/2/2017.
 */

public class Calculator extends AppCompatActivity {

    private static double num1 = 0.0;
    private static double num2 = 0.0;
    private static double result = 0.0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

    }

    public void onSumClick(View view){
        EditText ed1 = (EditText)findViewById(R.id.editText);
        EditText ed2 = (EditText)findViewById(R.id.editText2);
        TextView t1 = (TextView)findViewById(R.id.tView);

        String n1 = ed1.getText().toString();
        String n2 = ed2.getText().toString();

        if(TextUtils.isEmpty(n1) || TextUtils.isEmpty(n2)) {
            if(TextUtils.isEmpty(n1))
                ed1.setError("Enter a Number");
            if(TextUtils.isEmpty(n2))
                ed2.setError("Enter a Number");
            return;
        }
            num1 = Double.parseDouble(ed1.getText().toString());
            num2 = Double.parseDouble(ed2.getText().toString());
            result = num1 + num2;

            t1.setText(Double.toString(result));
    }

    public void onSubClick(View view){
        EditText ed1 = (EditText)findViewById(R.id.editText);
        EditText ed2 = (EditText)findViewById(R.id.editText2);
        TextView t1 = (TextView)findViewById(R.id.tView);

        String n1 = ed1.getText().toString();
        String n2 = ed2.getText().toString();

        if(TextUtils.isEmpty(n1) || TextUtils.isEmpty(n2)) {
            if(TextUtils.isEmpty(n1))
                ed1.setError("Enter a Number");
            if(TextUtils.isEmpty(n2))
                ed2.setError("Enter a Number");
            return;
        }
        num1 = Double.parseDouble(ed1.getText().toString());
        num2 = Double.parseDouble(ed2.getText().toString());
        result = num1 - num2;

        t1.setText(Double.toString(result));
    }

    public void onMulClick(View view){
        EditText ed1 = (EditText)findViewById(R.id.editText);
        EditText ed2 = (EditText)findViewById(R.id.editText2);
        TextView t1 = (TextView)findViewById(R.id.tView);

        String n1 = ed1.getText().toString();
        String n2 = ed2.getText().toString();

        if(TextUtils.isEmpty(n1) || TextUtils.isEmpty(n2)) {
            if(TextUtils.isEmpty(n1))
                ed1.setError("Enter a Number");
            if(TextUtils.isEmpty(n2))
                ed2.setError("Enter a Number");
            return;
        }
        num1 = Double.parseDouble(ed1.getText().toString());
        num2 = Double.parseDouble(ed2.getText().toString());
        result = num1 * num2;

        t1.setText(Double.toString(result));
    }

    public void onDivClick(View view){
        EditText ed1 = (EditText)findViewById(R.id.editText);
        EditText ed2 = (EditText)findViewById(R.id.editText2);
        TextView t1 = (TextView)findViewById(R.id.tView);

        String n1 = ed1.getText().toString();
        String n2 = ed2.getText().toString();

        if(TextUtils.isEmpty(n1) || TextUtils.isEmpty(n2)) {
            if(TextUtils.isEmpty(n1))
                ed1.setError("Enter a Number");
            if(TextUtils.isEmpty(n2))
                ed2.setError("Enter a Number");
            return;
        }
        num1 = Double.parseDouble(ed1.getText().toString());
        num2 = Double.parseDouble(ed2.getText().toString());

        if(num2 != 0) {
            result = num1 / num2;
                t1.setText(Double.toString(result));
        }
        else{
            t1.setText("Error: Div Zero");
        }
    }

    public void Ganesh(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Main"))
        {
            Intent ganesh = new Intent(this,MainActivity.class);
            startActivity(ganesh);
            finish();
        }
    }

}
