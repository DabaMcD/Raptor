package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

class Jet {
    private float x;
    float y;
    private double sca, x2, y2, i;
    String height;
    private Paint paint;
    Jet(float x, float y) {
        height = "down";
        this.x = x;
        this.y = y;
        sca = 1.0;
        x2 = 5;
        y2 = 5;
        i = 0;
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        canvas.scale((float) sca, (float) sca);
        canvas.rotate((float) Math.toRadians(Math.cos(i * 30)));

        // Shadow
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(75, 0, 0, 0));
        Path shadow = new Path();
        shadow.moveTo((float) x2, (float) (-12 + y2));
        shadow.lineTo((float) (-20 + x2), (float) (24 - 12 + y2));
        shadow.lineTo((float) (-11 + x2), (float) (20 - 12 + y2));
        shadow.lineTo((float) (-5 + x2), (float) (23 - 12 + y2));
        shadow.lineTo((float) (-2 + x2), (float) (21 - 12 + y2));
        shadow.lineTo((float) x2, (float) (20 - 12 + y2));
        shadow.close();
        canvas.drawPath(shadow, paint);

        Path shadow2 = new Path();
        shadow2.moveTo((float) x2, (float) (-12 + y2));
        shadow2.lineTo((float) (20 + x2), (float) (24 - 12 + y2));
        shadow2.lineTo((float) (11 + x2), (float) (20 - 12 + y2));
        shadow2.lineTo((float) (5 + x2), (float) (23 - 12 + y2));
        shadow2.lineTo((float) (2 + x2), (float) (21 - 12 + y2));
        shadow2.lineTo((float) x2, (float) (20 - 12 + y2));
        shadow2.close();
        canvas.drawPath(shadow2, paint);

        // Jet
        canvas.translate(-5, -5);

        paint.setColor(Color.rgb(255, 0, 0));
        if ((Math.cos(i * 6) * 20) >= 0) {
            triangle(-7, 20 - 12, 7, 21 - 12, 0, (float) (Math.cos(i * 6) * 20), canvas);
        } else {
            triangle(-7, 20 - 12, 7, 21 - 12, 0, (float) (-Math.cos(i * 6) * 20), canvas);
        }

        paint.setColor(Color.rgb(32, 125, 247));
        Path realJet = new Path();
        realJet.moveTo(0, -12);
        realJet.lineTo(-20, 24 - 12);
        realJet.lineTo(-11, 20 - 12);
        realJet.lineTo(-5, 23 - 12);
        realJet.lineTo(-2, 21 - 12);
        realJet.lineTo(0, 20 - 12);
        realJet.close();
        canvas.drawPath(realJet, paint);

        paint.setColor(Color.rgb(20, 101, 207));
        Path realJet2 = new Path();
        realJet2.moveTo(0, -12);
        realJet2.lineTo(20, 24 - 12);
        realJet2.lineTo(11, 20 - 12);
        realJet2.lineTo(5, 23 - 12);
        realJet2.lineTo(2, 21 - 12);
        realJet2.lineTo(0, 20 - 12);
        realJet2.close();
        canvas.drawPath(realJet2, paint);

        canvas.restore();

        i ++;
    }
    private void triangle(float X1, float Y1, float X2, float Y2, float X3, float Y3, Canvas canvas) {
        Path path = new Path();
        path.moveTo(X1, Y1);
        path.lineTo(X2, Y2);
        path.lineTo(X3, Y3);
        path.close();
        canvas.drawPath(path, paint);
    }
}
