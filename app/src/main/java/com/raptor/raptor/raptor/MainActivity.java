package com.raptor.raptor.raptor;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {
    Thread mainThread;
    GameScreen gameScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setScreenDims();

        gameScreen = findViewById(R.id.gameScreen);
        Touch.setTouchListener(gameScreen);
        createAndStartMainThread();
    }
    private void createAndStartMainThread() {
        mainThread = new Thread() {
            public void run() {
                while (mainThread.isAlive()) {
                    long bob = System.currentTimeMillis();
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gameScreen.draw();
                            }
                        });
                        if(bob - System.currentTimeMillis() + 20 > 0) {
                            Thread.sleep(bob - System.currentTimeMillis() + 20);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainThread.start();
    }
    private void setScreenDims() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Screen.width = size.x;
        Screen.height = size.y - getStatusBarHeight();
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
