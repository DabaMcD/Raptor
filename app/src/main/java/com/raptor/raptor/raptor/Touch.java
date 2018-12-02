package com.raptor.raptor.raptor;

import android.view.MotionEvent;
import android.view.View;

class Touch {
    static boolean isTouching = false;

    static void setTouchListener(View view, final GameScreen gameScreen) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isTouching = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTouching = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isTouching = false;

                        if (gameScreen.dead && !gameScreen.start && !gameScreen.replay) {
                            gameScreen.dead = false;
                            gameScreen.start = true;
                        }
                        if (gameScreen.dead && gameScreen.start && gameScreen.f2 >= 250d) {
                            gameScreen.replay = true;
                            gameScreen.score = Math.round(gameScreen.y / 150);
                        }
                        break;
                }
                return true;
            }
        });
    }
}