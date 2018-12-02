package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class DieText {
    private Paint paint;
    DieText() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void draw(Canvas canvas, double f2) {
        paint.setColor(Color.argb((int) f2, 45, 45, 45));
        paint.setTextSize(50);
        canvas.drawText("YOU DIED", Screen.width / 2, Screen.height - 200 - 100, paint);
        paint.setTextSize(15);
        canvas.drawText("CLICK TO RESTART", Screen.width / 2, Screen.height - 200 - 65, paint);
    }
}
