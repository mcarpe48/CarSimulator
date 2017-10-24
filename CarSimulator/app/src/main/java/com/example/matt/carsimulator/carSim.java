package com.example.matt.carsimulator;


import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.math.*;

public class carSim extends Activity implements OnTouchListener, View.OnClickListener {

    private ImageView wheel, car;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;

    private double check = 0;

    private double xPos = 0;
    private double yPos = 0;

    private float xCar = 0;
    private float yCar = 0;

    private boolean engine = false;



    Button gas, brake, engineControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_sim);

        wheel=(ImageView)findViewById(R.id.steering_wheel);
        wheel.setOnTouchListener(this);

        car=(ImageView)findViewById(R.id.ferrCar);

        //car.setOnTouchListener(this);

        gas = (Button)findViewById(R.id.gas);
        brake = (Button)findViewById(R.id.brake);
        engineControl = (Button)findViewById(R.id.engineControl);

        gas.setOnTouchListener(this);//new View.OnTouchListener(){

//            private Handler mHandler;
//
//            @Override public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        if (mHandler != null) return true;
//                        mHandler = new Handler();
//                        mHandler.postDelayed(mAction, 500);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (mHandler == null) return true;
//                        mHandler.removeCallbacks(mAction);
//                        mHandler = null;
//                        break;
//                }
//                return false;
//            }
//
//            Runnable mAction = new Runnable() {
//                @Override public void run() {
//
//                    final double rise = Math.toDegrees(  Math.sin(Math.toRadians(wheel.getRotation()))  )*5;
//                    final double run  = Math.toDegrees(  Math.cos(Math.toRadians(wheel.getRotation()))  )*5;
//
//                    RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
//                            car.getLayoutParams();
//
////                    car.setX((float) (car.getX() + run)); //pay attention here
////                    car.setY((float) (car.getY() + rise));//pay attention here
////                    mParams.topMargin -= 5;
//                    mParams.setMargins((int)(car.getY() + rise),(int)(car.getX() + run),0,0);
//                    car.setLayoutParams(mParams);
//                    mHandler.postDelayed(this, 100);
//                }
//            };
//
//        });

        brake.setOnTouchListener(this);
        engineControl.setOnClickListener(this);



    }


    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = wheel.getWidth() / 2;          //Wheel Vars
        final float yc = wheel.getHeight() / 2;

        final float xc2 = car.getWidth() / 2;           //Car Vars
        final float yc2 = car.getHeight()/ 2;

        final float x = event.getX();
        final float y = event.getY();

        final double rise = Math.toDegrees(  Math.sin(Math.toRadians(car.getRotation()))  )*5;
        final double run  = Math.toDegrees(  Math.cos(Math.toRadians(car.getRotation()))  )*5;

        final float frontOfCar = car.getTop();
//        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
//
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE);
//        anim.setDuration(700);

        switch(v.getId()) {
            case R.id.steering_wheel:
                {

                    switch (event.getAction() & MotionEvent.ACTION_MASK)
                    {
                            case MotionEvent.ACTION_DOWN: {
                                wheel.clearAnimation();
                                car.clearAnimation();
                                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
//                                mCurrCarAngle = Math.toDegrees(Math.atan2(xCar-xc2, yc2 -yCar));



                                break;
                            }
                            case MotionEvent.ACTION_MOVE: {
                                mPrevAngle = mCurrAngle;
//                                mPrevCarAngle = mCurrCarAngle;
                                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
//                                mCurrCarAngle = Math.toDegrees(Math.atan2(xCar-xc2, yc2 -yCar));

                                if(mCurrAngle < 0){
                                    check = Math.abs(mCurrAngle) + 90;
                                }
                                else{
                                    check = Math.abs((mCurrAngle+ 270)%360 -360);
                                }
                                double rad = check*(Math.PI/180);
                                xPos = 6*Math.cos(rad);
                                yPos = 6*Math.sin(rad);

                                if(xPos > yPos){
                                    yPos = Math.floor(yPos);
                                    xPos = Math.ceil(xPos);
                                }
                                else{
                                    xPos = Math.floor(xPos);
                                    yPos = Math.ceil(yPos);
                                }



//                                if(mCurrAngle >=180 && mCurrAngle < 270){
//                                    xPos = -1;
//                                    yPos = -1;
//                                }
//                                if(mCurrAngle >=270 && mCurrAngle < 360){
//                                    xPos = -1;
//                                    yPos = 1;
//                                }

                                //if (mCurrAngle <= 90 && mCurrAngle >= -90) {
                                    animate(mPrevAngle, mCurrAngle, 0);
                                    //car.startAnimation(anim);
                                //}
                                System.out.println(mCurrAngle);
                                break;
                            }
                            case MotionEvent.ACTION_UP: {
                                mPrevAngle = mCurrAngle = 0;
                                break;
                            }
                    }
                break;
                }
            case R.id.gas:{
                if(engine) {


                    RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                            car.getLayoutParams();

//                if(!init){
//                    mParams.topMargin = 500;
//                    mParams.leftMargin = 275;
//                    init = true;
//                }


//                    mParams.rightMargin = 0;
//                    mParams.bottomMargin = 0;

                    xCar = car.getX();
                    yCar = car.getY();

                    TextView position = (TextView) findViewById(R.id.position);

                    position.setText("(" + xCar + "," + yCar + ")");


//                if(((xCar > 60) && (xCar < 1340)) &&  ((yCar > 50) && (yCar < 1300))){  //Outter Box Limits
//                    mParams.bottomMargin += yPos;
//                    mParams.leftMargin -= xPos;
//                    car.setLayoutParams(mParams);
//                }
                    if (((xCar > 60) && (xCar < 1340)) && ((yCar > 50) && (yCar < 155))) {  //Top Rectangle
                        mParams.bottomMargin += yPos;
                        mParams.leftMargin += xPos;
                        car.setLayoutParams(mParams);
                    } else if (((xCar > 60) && (xCar < 1340)) && ((yCar > 1160) && (yCar < 1300))) { //Bottom Rectangle
                        mParams.bottomMargin += yPos;
                        mParams.leftMargin += xPos;
                        car.setLayoutParams(mParams);
                    } else if (((xCar > 60) && (xCar < 230)) && ((yCar > 50) && (yCar < 1300))) { //Left Rectangle
                        mParams.bottomMargin += yPos;
                        mParams.leftMargin += xPos;
                        car.setLayoutParams(mParams);
                    } else if (((xCar > 1170) && (xCar < 1340)) && ((yCar > 50) && (yCar < 1300))) { //Right Rectangle
                        mParams.bottomMargin += yPos;
                        mParams.leftMargin += xPos;
                        car.setLayoutParams(mParams);
                    } else {
                        if ((xCar <= 60) || (xCar <= 1170 && xCar >= 1160)) {                                 //Left Bounce
//                        mParams.topMargin = (int) car.getY() + 5;
                            xPos = 0;
                            if (mCurrAngle <= -105 || mCurrAngle >= -75)
                                mParams.leftMargin += 2;
//                        mParams.leftMargin = (int) car.getX() + 5;
                            car.setLayoutParams(mParams);
                        } else if ((xCar >= 1340) || (xCar >= 230 && xCar <= 240)) {                        //Right Bounce
//                        mParams.topMargin = (int) car.getY() + 5;
                            xPos = 0;
                            if (mCurrAngle <= 120 || mCurrAngle == 150)
                                mParams.leftMargin -= 2;
//                        mParams.leftMargin = (int) car.getX() + 5;
                            car.setLayoutParams(mParams);
                        } else if ((yCar <= 80)) {                                     //outer box Top Bounce
//                        mParams.topMargin = (int) car.getY() + 5;
                            yPos = 0;
                            if (mCurrAngle <= -15 || mCurrAngle >= 15)
                                mParams.bottomMargin -= 2;
//                        mParams.leftMargin = (int) car.getX() + 5;
                            car.setLayoutParams(mParams);
                        } else if ((yCar >= 1300)) {                             //outter box Bottom Bounce
//                        mParams.bottomMargin = (int) car.getY() - 5;
//                        mParams.leftMargin = (int) car.getX() - 5;
                            yPos = 0;
                            if (mCurrAngle <= -15 || mCurrAngle >= 15)
                                mParams.bottomMargin += 2;

                            car.setLayoutParams(mParams);
                        }
                        if ((yCar >= 155) && (yCar <= 165)) {                  //inner box Top Bounce
//                      yPos = 0;
                            if (mCurrAngle <= -15 || mCurrAngle >= 15)
                                mParams.bottomMargin += 2;

                            car.setLayoutParams(mParams);
                        } else if ((yCar <= 1160)) {                                 //inner box Bottom Bounce
                            yPos = 0;
                            if (mCurrAngle >= -15 || mCurrAngle <= 15)
                                mParams.bottomMargin -= 2;

                            car.setLayoutParams(mParams);
                        }


                    }

                }
                System.out.println("X: " + car.getX() + "Y: " + car.getY());
                    break;
            }
            case R.id.brake:{
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();
                mParams.bottomMargin -= yPos;
                mParams.leftMargin += xPos;
//                mParams.rightMargin = 0;
//                mParams.bottomMargin = 0;
                car.setLayoutParams(mParams);
                break;
            }
        }
        return true;



    }



    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        TextView t1 = (TextView)(findViewById(R.id.steer_angle));

        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        wheel.startAnimation(rotate);
        System.out.println(mCurrAngle);
        t1.setText(Double.toString(Math.round(mCurrAngle*100.00)/100.00));

        final RotateAnimation rotate2 = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate2.setDuration(durationMillis);
        rotate2.setFillEnabled(true);
        rotate2.setFillAfter(true);
        car.startAnimation(rotate2);
        System.out.println(mCurrAngle);
//        t1.setText(Double.toString(Math.round(mCurrCarAngle*100.00)/100.00));

    }

    @Override
    public void onClick(View v) {
        TextView t2 = (TextView)(findViewById(R.id.pos));
        //int position;

        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.engineControl:
            {
                engine = !engine;
                break;
            }


            case R.id.gas:
            {
                //Toast.makeText(getApplication(),"DOWN",Toast.LENGTH_SHORT).show();


                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();
                mParams.topMargin += 20;
                car.setLayoutParams(mParams);
                break;
            }


            case R.id.brake:
            {
               // Toast.makeText(getApplication(),"LEFT",Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();
                mParams.leftMargin -= 20;
                car.setLayoutParams(mParams);
                break;
            }

        }
    }


    }


