package com.example.somegame;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Test extends SurfaceView implements Runnable{
    private Paint paint;
    private SurfaceHolder surfaceholder;
    private Canvas canvas;
    private Bitmap bitmap;
    private int bitmapid;
    private float x;
    private float y;
    private int xMax = 100;
    private int yMax = 150;
    private float PartOfScreenX;
    private float PartOfScreenY;
    private int[] mapX = new int[xMax];
    private int[] mapY = new int[yMax];
    private float size = 1;

    private boolean running = false;

    private Thread TestThread;

    public Test (Context context) {
        super(context);
        surfaceholder = getHolder();
        paint = new Paint();
        bitmapid = R.drawable.slayer;
        init(context);

        TestThread = new Thread();
        TestThread.start();
    }
    private void init(Context context) {
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapid);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * PartOfScreenX), (int)(size * PartOfScreenY), false);
        cBitmap.recycle();
    }

    public void run() {
        running = true;
        while (running == true) {

            for (int xx = 0; xx <= xMax; xx++) {
                mapX[xx] = xx;
                x++;
                try {
                    draw();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    xx--;
                    x--;
                }
                if (x == xMax) {
                    y++;
                    xx = 0;
                    x = 0;
                    if (y == yMax) {
                        running = false;
                    }
                }
            }
        }
    }

    private void drow(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x * PartOfScreenX, y * PartOfScreenY, paint);
    }
    private void draw() throws InterruptedException {
        try {
            PartOfScreenX = surfaceholder.getSurfaceFrame().width() / xMax;
            PartOfScreenY = surfaceholder.getSurfaceFrame().height() / yMax;

            canvas = surfaceholder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            drow(paint, canvas);

            surfaceholder.unlockCanvasAndPost(canvas);
        }
        catch (Exception e) {
            e.printStackTrace();
            TestThread.sleep(100);
            draw();
        }
    }

}
