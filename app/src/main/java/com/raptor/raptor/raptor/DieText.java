package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class DieText {
    private Paint paint;
    DieText() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }
    void draw(Canvas canvas, int f2, int dieRectStrength) {
        paint.setColor(Color.argb(dieRectStrength, 255, 255, 255));
        canvas.drawRect(-10, Screen.height * 12 / 30, Screen.width + 10, Screen.height * 2 / 3, paint);
        paint.setColor(Color.argb(f2, 45, 45, 45));
        paint.setTextSize(Screen.width / 8);
        canvas.drawText("YOU DIED", Screen.width / 2, Screen.height / 2, paint);
        paint.setTextSize(Screen.width / 30);
        canvas.drawText("CLICK TO RESTART", Screen.width / 2, Screen.height / 16 * 9, paint);
    }
}
