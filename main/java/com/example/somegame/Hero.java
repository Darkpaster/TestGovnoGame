package com.example.somegame;

import android.content.Context;
import android.widget.TextView;

public class Hero extends SpaceBody{
    public Hero(Context context) {
        bitmapId = R.drawable.slayer; // определяем начальные параметры
        size = 4;
        x=14;
        y=14;
        speed = (float) 0.1;
        speed1 = (float) 0.2;


        init(context);
    }


    @Override
    public void update() {
        if(MainActivity.isLeftPressed == true){
            x -= speed;
        }
        if(MainActivity.isRightPressed == true){
            x += speed1;

        }
        if(MainActivity.isUpPressed == true){
            y -= speed1;
        }
        if(MainActivity.isDownPressed == true){
            y += speed;

        }
    }
}
