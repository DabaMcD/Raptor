package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class InitText {
    private Paint paint;
    InitText() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void draw(Canvas canvas, float f, float y) {
        paint.setTextSize(50);
        paint.setColor(Color.argb((int) f, 45, 45, 45));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("RAPTOR", Screen.width / 2, y + Screen.height / 2, paint);
        paint.setTextSize(15);
        canvas.drawText("CLICK TO START", Screen.width / 2, y + (Screen.height / 2) + 35, paint);
        paint.setTextSize(Screen.width / 40);
        if(paint.measureText("Hold to dive under red obstacles, let off to fly over black ones") >= Screen.width * 9 / 10) {
            canvas.drawText("Hold to dive under red obstacles,", Screen.width / 2, (float) (y + Screen.height - 30 - paint.getTextSize() * 1.75), paint);
            canvas.drawText("let off to fly over black ones", Screen.width / 2, y + Screen.height - 30, paint);
        } else { // todo: make sure code above doesn't need adjusting
            canvas.drawText("Hold to dive under red obstacles, let off to fly over black ones", Screen.width / 2, y + Screen.height - 30, paint);
        }
    }
}
