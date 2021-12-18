package com.example.somegame;


import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static boolean isUpPressed = false;
    public static boolean isDownPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isLeftPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        GameView gameview = new GameView(this);
		Test test = new Test(this);
        LinearLayout GameLayout = (LinearLayout) findViewById(R.id.AmuletLayout);
        //GameLayout.addView(gameview);
		GameLayout.addView(test);
        TextView button1 = (TextView) findViewById(R.id.RightButton);
        TextView button2 = (TextView) findViewById(R.id.LeftButton);
        TextView button3 = (TextView) findViewById(R.id.UpButton);
        TextView button4 = (TextView) findViewById(R.id.DownButton);
        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.RightButton:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
            case R.id.LeftButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.UpButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isUpPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isUpPressed = false;
                        break;
                }
            case R.id.DownButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isDownPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isDownPressed = false;
                        break;
                }
                break;
        }
        return true;
    }
}