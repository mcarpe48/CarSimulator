package com.example.matt.carsimulator;


import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private int isDriving = 0;
    private long startTime = System.currentTimeMillis();




    Button gas, brake, engineControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_sim);

        wheel=(ImageView)findViewById(R.id.steering_wheel);
        wheel.setOnTouchListener(this);

        car=(ImageView)findViewById(R.id.ferrCar);

        gas = (Button)findViewById(R.id.gas);
        brake = (Button)findViewById(R.id.brake);
        engineControl = (Button)findViewById(R.id.engineControl);

        gas.setOnTouchListener(this);

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



        switch(v.getId()) {
            case R.id.steering_wheel: {
                if (isDriving != 0) {
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN: {
                            wheel.clearAnimation();
                            car.clearAnimation();
                            mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                            break;
                        }
                        case MotionEvent.ACTION_MOVE: {
                            mPrevAngle = mCurrAngle;
                            mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));

                            if (mCurrAngle < 0) {
                                check = Math.abs(mCurrAngle) + 90;
                            } else {
                                check = Math.abs((mCurrAngle + 270) % 360 - 360);
                            }
                            double rad = check * (Math.PI / 180);
                            xPos = 10 * Math.cos(rad);
                            yPos = 10 * Math.sin(rad);

                            if (xPos > yPos) {
                                yPos = Math.floor(yPos);
                                xPos = Math.ceil(xPos);
                            } else {
                                xPos = Math.floor(xPos);
                                yPos = Math.ceil(yPos);
                            }
                            animate(mPrevAngle, mCurrAngle, 0);

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
            }
            case R.id.gas:{
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN: {
                        isDriving = 1;
//                        drive();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        drive();
                        break;
                    }
                    case MotionEvent.ACTION_BUTTON_RELEASE: {
                        isDriving = 0;
                        break;
                    }
                }
                break;

            }
            case R.id.brake:{
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();
                mParams.bottomMargin -= yPos;
                mParams.leftMargin += xPos;
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
    }

    public void drive(){
            if(engine) {

                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();

                xCar = car.getX();
                yCar = car.getY();

                TextView position = (TextView) findViewById(R.id.position);

                position.setText("(" + xCar + "," + yCar + ")");

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
                        xPos = 0;
                        if (mCurrAngle <= -105 || mCurrAngle >= -75)
                            mParams.leftMargin += 2;
                        car.setLayoutParams(mParams);
                    } else if ((xCar >= 1340) || (xCar >= 230 && xCar <= 240)) {                        //Right Bounce
                        xPos = 0;
                        if (mCurrAngle <= 120 || mCurrAngle == 150)
                            mParams.leftMargin -= 2;
                        car.setLayoutParams(mParams);
                    } else if ((yCar <= 80)) {                                     //outer box Top Bounce
                        yPos = 0;
                        if (mCurrAngle <= -15 || mCurrAngle >= 15)
                            mParams.bottomMargin -= 2;
                        car.setLayoutParams(mParams);
                    } else if ((yCar >= 1300)) {                             //outter box Bottom Bounce
                        yPos = 0;
                        if (mCurrAngle <= -15 || mCurrAngle >= 15)
                            mParams.bottomMargin += 2;

                        car.setLayoutParams(mParams);
                    }
                    if ((yCar >= 155) && (yCar <= 165)) {                  //inner box Top Bounce
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
    }

    @Override
    public void onClick(View v) {
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

                break;
            }


            case R.id.brake:
            {
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams)
                        car.getLayoutParams();
                mParams.leftMargin -= 20;
                car.setLayoutParams(mParams);
                break;
            }

        }
    }

}


