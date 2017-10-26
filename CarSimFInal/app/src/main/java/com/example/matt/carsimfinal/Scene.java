package com.example.matt.carsimfinal;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Matt on 10/24/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
