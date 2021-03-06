package com.raptor.raptor.raptor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

class Jet {
    private float x;
    float y;
    double sca, x2, y2;
    private double i;
    String height;
    double scale;
    private Paint paint;

    Jet(float x, float y) {
        height = "down";
        this.x = x;
        this.y = y;
        sca = 1.0;
        x2 = 5;
        y2 = 5;
        i = 0;
        scale = Screen.width / 400;
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        canvas.scale((float) sca, (float) sca);
        canvas.rotate((float) Math.cos(Math.toRadians(i * 30)));

        // Shadow
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(75, 0, 0, 0));
        Path shadow = new Path();
        shadow.moveTo((float) (x2 * scale), (float) ((-12 + y2) * scale));
        shadow.lineTo((float) ((-20 + x2) * scale), (float) ((24 - 12 + y2) * scale));
        shadow.lineTo((float) ((-11 + x2) * scale), (float) ((20 - 12 + y2) * scale));
        shadow.lineTo((float) ((-5 + x2) * scale), (float) ((23 - 12 + y2) * scale));
        shadow.lineTo((float) ((-2 + x2) * scale), (float) ((21 - 12 + y2) * scale));
        shadow.lineTo((float) (x2 * scale), (float) ((20 - 12 + y2) * scale));
        shadow.close();
        canvas.drawPath(shadow, paint);

        Path shadow2 = new Path();
        shadow2.moveTo((float) (x2 * scale), (float) ((-12 + y2) * scale));
        shadow2.lineTo((float) ((20 + x2) * scale), (float) ((24 - 12 + y2) * scale));
        shadow2.lineTo((float) ((11 + x2) * scale), (float) ((20 - 12 + y2) * scale));
        shadow2.lineTo((float) ((5 + x2) * scale), (float) ((23 - 12 + y2) * scale));
        shadow2.lineTo((float) ((2 + x2) * scale), (float) ((21 - 12 + y2) * scale));
        shadow2.lineTo((float) (x2 * scale), (float) ((20 - 12 + y2) * scale));
        shadow2.close();
        canvas.drawPath(shadow2, paint);

        // Jet
        canvas.translate((float) (-5 * scale), (float) (-5 * scale));

        // Fire shooting out the back of jet
        paint.setColor(Color.rgb(255, 0, 0));
        Path path = new Path();
        path.moveTo((float) (-7 * scale), (float) ((20 - 12) * scale));
        path.lineTo((float) (7 * scale), (float) ((21 - 12) * scale));
        path.lineTo(0, (float) ((Math.abs(Math.cos(Math.toRadians(i * 6))) * 20) * scale));
        path.close();
        canvas.drawPath(path, paint);

        paint.setColor(Color.rgb(32, 125, 247));
        Path realJet = new Path();
        realJet.moveTo(0, (float) (-12 * scale));
        realJet.lineTo((float) (-20 * scale), (float) ((24 - 12) * scale));
        realJet.lineTo((float) (-11 * scale), (float) ((20 - 12) * scale));
        realJet.lineTo((float) (-5 * scale), (float) ((23 - 12) * scale));
        realJet.lineTo((float) (-2 * scale), (float) ((21 - 12) * scale));
        realJet.lineTo(0, (float) ((20 - 12) * scale));
        realJet.close();
        canvas.drawPath(realJet, paint);

        paint.setColor(Color.rgb(20, 101, 207));
        Path realJet2 = new Path();
        realJet2.moveTo(0, (float) (-12 * scale));
        realJet2.lineTo((float) (20 * scale), (float) ((24 - 12) * scale));
        realJet2.lineTo((float) (11 * scale), (float) ((20 - 12) * scale));
        realJet2.lineTo((float) (5 * scale), (float) ((23 - 12) * scale));
        realJet2.lineTo((float) (2 * scale), (float) ((21 - 12) * scale));
        realJet2.lineTo(0, (float) ((20 - 12) * scale));
        realJet2.close();
        canvas.drawPath(realJet2, paint);

        canvas.restore();

        i ++;
    }
}
