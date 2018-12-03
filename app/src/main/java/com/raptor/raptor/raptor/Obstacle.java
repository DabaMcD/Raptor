package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

class Obstacle {
    int type;
    private int i;
    private Paint paint;

    Obstacle(int type, int i) {
        this.type = type;
        this.i = -i * 250;
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    boolean collide(Jet c, float y) {
        switch (this.type) {
            case 1:
                if (c.y + 10 > (y + this.i) - 90 && c.y < (y + this.i) + 118) {
                    if (c.height.equals("up")) {
                        return true;
                    }
                }
                break;
            case 2:
                if (c.y + 10 > (y + this.i) && c.y < (y + this.i) + 78) {
                    if (c.height.equals("up")) {
                        return true;
                    }
                }
                break;
            default:
                if (c.y + 10 > (y + this.i) && c.y < (y + this.i) + 48) {
                    if (c.height.equals("down")) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }
    void draw(Canvas canvas, float y, Jet c) {
        canvas.save();
        // The transformations below do nothing but center the obstacles
        canvas.translate(Screen.width / 2, 0);
        canvas.scale(Screen.width / 400, 1);
        canvas.translate(-200, 0);
        switch (this.type) {
            // small red
            case 1:
                paint.setColor(Color.argb(100, 0, 0, 0));
                rect(205, y + this.i + 15, 150, 180, canvas);

                paint.setColor(Color.rgb(242, 83, 83));
                rect(200, y + this.i, 150, 180, canvas);
                quad(125, y + this.i + 90, 275, y + this.i + 90, 270, y + this.i + 95, 130, y + this.i + 95, canvas);
                paint.setColor(Color.argb(100, 0, 0, 0));
                quad(125, y + this.i + 90, 275, y + this.i + 90, 270, y + this.i + 95, 130, y + this.i + 95, canvas);
                break;
            // big red
            case 2:
                paint.setColor(Color.argb(100, 0, 0, 0));
                rect(205, y + this.i + 15, 150, -100, canvas);

                paint.setColor(Color.rgb(242, 83, 83));
                rect(200, y + this.i, 150, -100, canvas);
                quad(125, y + this.i + 50, 275, y + this.i + 50, 270, y + this.i + 55, 130, y + this.i + 55, canvas);
                paint.setColor(Color.argb(100, 0, 0, 0));
                quad(125, y + this.i + 50, 275, y + this.i + 50, 270, y + this.i + 55, 130, y + this.i + 55, canvas);
                break;

            // black
            default:
                paint.setColor(Color.argb(100, 0, 0, 0));
                rect(205, y + this.i + 15, 100, 50, canvas);

                paint.setColor(Color.rgb(41, 41, 41));
                rect(200, y + this.i, 100, 50, canvas);
                quad(150, y + this.i + 25, 250, y + this.i + 25, 245, y + this.i + 30, 155, y + this.i + 30, canvas);
                paint.setColor(Color.argb(100, 0, 0, 0));
                quad(150, y + this.i + 25, 250, y + this.i + 25, 245, y + this.i + 30, 155, y + this.i + 30, canvas);
                break;
        }
        canvas.restore();
    }
    boolean die(float y) {
        return (y + this.i > Screen.height + 100);
    }
    private void quad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Canvas canvas) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.close();
        canvas.drawPath(path, paint);
    }
    private void rect(float centerX, float centerY, float width, float height, Canvas canvas) {
        canvas.drawRect(centerX - (width / 2), centerY - (height / 2), centerX + (width / 2), centerY + (height / 2), paint);
    }
}
