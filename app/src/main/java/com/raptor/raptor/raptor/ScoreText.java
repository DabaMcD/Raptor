package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

class ScoreText {
    private Paint paint;
    private int highscore;

    ScoreText(int highscore) {
        this.highscore = highscore;
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void draw(Canvas canvas, ArrayList<Float> sc, double f3, double f4, float y) {
        paint.setTextSize(Screen.width / 20);
        paint.setColor(Color.argb((int) f3, 45, 45, 45));
        canvas.drawText("Highscore: " + String.valueOf(highscore), Screen.width - 20 - paint.measureText("Highscore: " + String.valueOf(highscore)), Screen.height / 20, paint);
        paint.setTextSize(sc.get(2));
        paint.setColor(Color.argb((int) f3, 45, 45, 45));
        canvas.drawText("Score: " + String.valueOf(Math.round(y / 150)), sc.get(0), sc.get(1), paint);

        paint.setColor(Color.argb((int) f4, 235, 235, 235));
        canvas.drawRect(-10, -10, Screen.width + 10, Screen.height + 10, paint);
    }
    float halfTextWidth(float y) {
        return paint.measureText("Score: " + String.valueOf(Math.round(y / 150)));
    }
}
