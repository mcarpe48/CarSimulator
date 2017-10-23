package com.example.matt.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Matt on 10/3/2017.
 */

public class CheckBoxes extends AppCompatActivity {

    private RadioGroup radio_g;
    private RadioButton s1, s2, s3, s4, s5, radio_b;
    private Button submit;
    private static double sum, count, avg = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        addListenerOnSelect();

    }

    public void addListenerOnSelect(){
        radio_g = (RadioGroup)findViewById(R.id.rg_rating);
        s1 = (RadioButton) findViewById(R.id.radioButton);
        s2 = (RadioButton)findViewById(R.id.radioButton2);
        s3 = (RadioButton)findViewById(R.id.radioButton3);
        s4 = (RadioButton)findViewById(R.id.radioButton4);
        s5 = (RadioButton)findViewById(R.id.radioButton5);
        //submit = (Button)findViewById(R.id.checkBut);


        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StringBuffer result = new StringBuffer();
                if(s1.isChecked())
                    Toast.makeText(CheckBoxes.this,"Thanks for the Feedback!",Toast.LENGTH_SHORT).show();

//                result.append("5 Stars : ").append(s1.isChecked());
//                Toast.makeText(CheckBoxes.this,result,Toast.LENGTH_LONG).show();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2.isChecked())
                    Toast.makeText(CheckBoxes.this,"Sorry it's not perfect!",Toast.LENGTH_SHORT).show();
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s3.isChecked())
                    Toast.makeText(CheckBoxes.this,"Mediocre, eh?",Toast.LENGTH_SHORT).show();
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s4.isChecked())
                    Toast.makeText(CheckBoxes.this,"ARE YOU NOT ENTERTAINED???",Toast.LENGTH_SHORT).show();
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s5.isChecked()) {
                    Toast.makeText(CheckBoxes.this, "Seriously!!!!", Toast.LENGTH_SHORT).show();
                    Ganesh(v);
                }
            }
        });

    }

    public void averageRating(View view)
    {
        int selected_id = radio_g.getCheckedRadioButtonId();
        radio_b = (RadioButton)findViewById(selected_id);

        String result = radio_b.getText().toString();

        count = count + 1.0;

        switch(result){
            case "5 Stars":{
                sum += 5.0;
                break;
            }
            case "4 Stars":{
                sum += 4.0;
                break;
            }
            case "3 Stars":{
                sum += 3.0;
                break;
            }
            case "2 Stars":{
                sum += 2.0;
                break;
            }
            case "1 Star":{
                sum += 1.0;
                break;
            }
        }

        avg = sum/count;            //Why do you have to divide by 3?

        Toast.makeText(CheckBoxes.this, "Average Rating : " + Double.toString(avg),Toast.LENGTH_SHORT).show();

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
