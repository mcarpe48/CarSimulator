package com.example.matt.helloworld;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String hello;
    Button mybtn;
    TextView txtView;
    private EditText pwd;
    private Button show_pwd;

    public static final String MY_TAG = "custom_message";

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mybtn = (Button)findViewById(R.id.mybtn);
        txtView=(TextView)findViewById(R.id.txtView);

        hello = "Hello World!";
        txtView.setText(hello);
        txtView.setVisibility(View.GONE);
        mybtn.setOnClickListener(this);
        addListenerButton();


//        if(getIntent().getBooleanExtra("EXIT",false))
//            finish();

    }

    public void onClick(View view){
        txtView.setVisibility((txtView.getVisibility() == View.GONE)
                ? View.VISIBLE : View.GONE);
        Log.i(MY_TAG,"Button Clicked!");
    }

    public void addListenerButton(){
        pwd = (EditText)findViewById(R.id.editText3);
        show_pwd = (Button)findViewById(R.id.button5);

        show_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this, pwd.getText(),Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void closeApp(View view)
    {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Please don't go!!!").setCancelable(false)
                .setPositiveButton("Get me outta here!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                }).setNegativeButton("Okay...", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
        });

        AlertDialog alert = a_builder.create();
        alert.setTitle("Wait a minute!!!");
        alert.show();
    }

    public void Ganesh(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Calculator"))
        {
            Intent ganesh = new Intent(this,Calculator.class);
            startActivity(ganesh);
        }
        if(button_text.equals("Rating"))
        {
            Intent ganesh = new Intent(this,CheckBoxes.class);
            startActivity(ganesh);
        }
        if(button_text.equals("To Do List"))
        {
            Intent ganesh = new Intent(this,ToDoList.class);
            startActivity(ganesh);
        }
        if(button_text.equals("Clock"))
        {
            Intent ganesh = new Intent(this,alertDialog.class);
            startActivity(ganesh);
        }
    }




//    public void buttonOnClick(View v){
//        Button button = (Button)v;
//
//        ((Button) v).setText("Clicked");
//
//
//    }

}