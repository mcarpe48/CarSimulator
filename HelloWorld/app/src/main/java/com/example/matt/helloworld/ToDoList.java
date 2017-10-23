package com.example.matt.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Matt on 10/3/2017.
 */

public class ToDoList extends AppCompatActivity {

    private CheckBox s1, s2, s3, s4, s5;
    private Button submit;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        addListenerOnCheck();

    }

    public void addListenerOnCheck(){
        s1 = (CheckBox)findViewById(R.id.checkBox);
        s2 = (CheckBox)findViewById(R.id.checkBox2);
        s3 = (CheckBox)findViewById(R.id.checkBox3);
        s4 = (CheckBox)findViewById(R.id.checkBox4);
        s5 = (CheckBox)findViewById(R.id.checkBox5);
        //submit = (Button)findViewById(R.id.checkBut);


        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StringBuffer result = new StringBuffer();
                if(s1.isChecked())
                    Toast.makeText(ToDoList.this,"Most important meal of the day!",Toast.LENGTH_SHORT).show();

//                result.append("5 Stars : ").append(s1.isChecked());
//                Toast.makeText(ToDoList.this,result,Toast.LENGTH_LONG).show();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2.isChecked())
                    Toast.makeText(ToDoList.this,"Take a Deep Breath!",Toast.LENGTH_SHORT).show();
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s3.isChecked())
                    Toast.makeText(ToDoList.this,"Do You Feel the BURN?!?!?",Toast.LENGTH_SHORT).show();
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s4.isChecked())
                    Toast.makeText(ToDoList.this,"Glad that's over!!",Toast.LENGTH_SHORT).show();
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s5.isChecked()) {
                    Toast.makeText(ToDoList.this, "Now go to bed!", Toast.LENGTH_SHORT).show();
                    Ganesh(v);
                }

            }
        });

    }

    public void resetCheck(View view)
    {   s1 = (CheckBox)findViewById(R.id.checkBox);
        s2 = (CheckBox)findViewById(R.id.checkBox2);
        s3 = (CheckBox)findViewById(R.id.checkBox3);
        s4 = (CheckBox)findViewById(R.id.checkBox4);
        s5 = (CheckBox)findViewById(R.id.checkBox5);

        if(s1.isChecked())
            s1.setChecked(false);
        if(s2.isChecked())
            s2.setChecked(false);
        if(s3.isChecked())
            s3.setChecked(false);
        if(s4.isChecked())
            s4.setChecked(false);
        if(s5.isChecked())
            s5.setChecked(false);
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
