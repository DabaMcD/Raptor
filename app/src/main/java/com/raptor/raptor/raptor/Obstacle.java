package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

class Obstacle {
    private int type, i;
    private Paint paint;

    Obstacle(int type, int i) {
        this.type = type;
        this.i = -i * 250;
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void quad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Canvas canvas) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.close();
        canvas.drawPath(path, paint);
    }
}