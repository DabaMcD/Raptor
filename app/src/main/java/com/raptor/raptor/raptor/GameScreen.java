package com.raptor.raptor.raptor;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GameScreen extends View {
    private Context context;
    private Jet jet;
    private ArrayList<Obstacle> b;
    private int i;
    boolean dead, start, replay;
    float y;
    double speed = 5;
    double f4, f2, f3/*, f*/;
    ArrayList<Float> sc;
    InitText initText;
    DieText dieText;

    public GameScreen(Context context) {
        super(context);
        constructor(context);
    }
    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        constructor(context);
    }
    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }
    private void constructor(Context context) {
        this.context = context;
        b = new ArrayList<>();
        dead = true;
        replay = false;
        y = 0f;
        jet = new Jet(Screen.width / 2, Screen.height - 100);
        f4 = 255d;
        initText = new InitText();
        dieText = new DieText();
        start = false;
        f2 = 0d;
        f3 = 0d;
        sc = new ArrayList<>();
        sc.add(35f);
        sc.add(10f);
        sc.add(Screen.width / 40f);
        createJetAndFirstObstacles();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawBlackObstacles(canvas);
        jet.draw(canvas);
        drawRedObstacles(canvas);

        if (!dead) {
            f4 -= f4 / 20f;
            y += speed;
            speed += 0.003d;
            if (speed > 13d) {
                speed = 13d;
            }
        }

        touchyStuff();

        if(dead & start) {
            jet.height = "down";
            dieText.draw(canvas, f2);
            sc.set(2, (Screen.width / 20 - sc.get(2)) / 10);
            sc.set(1, (Screen.height - 200 - 30 - sc.get(1)) / 10);
            sc.set(0, (Screen.width/2 - sc.get(0)) / 10);
        }

        if (dead && start && !replay) {
            f2 += (255 - f2) / 10;
        }

        if(replay) {
            y -= y/10;
            f2 -= f2/10;
            f3 -= f3/10;
            sc.set(2, sc.get(2) + (15 - sc.get(2)) / 10);
            sc.set(1, sc.get(1) + (10 - sc.get(1)) / 10);
            sc.set(0, sc.get(0) + (35 - sc.get(0)) / 10);
            f4 += (255 - f4) / 5;
            if (y <= 1) {
                restartGame();
                dead = true;
                replay = false;
            }
        }
        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
    void createJetAndFirstObstacles() {
        jet = new Jet(Screen.width / 2, Screen.height - 100);

        b.add(new Obstacle(roundRandom(1, 3), i));
        i = 1;
        b.add(new Obstacle(roundRandom(1, 3), i));
        i = 2;
        for (i = 2; -i * 250 > -Screen.height - 800; i++) {
            b.add(new Obstacle(roundRandom(1, 4), i));
        }
    }
    int roundRandom(int min, int max) {
        if(min == max) {
            return min;
        }
        if(min > max) {
            int bob = max;
            max = min;
            min = bob;
        }
        return (int) Math.round((Math.random() * (max - min)) + min);
    }
    void drawBlackObstacles(Canvas canvas) {
        for (int j = b.size() - 1; j >= 0; j--) {
            if (b.get(j).type == 3 || b.get(j).type == 4) {
                b.get(j).draw(canvas, y);
                if(b.get(j).collide(jet, y)) {
                    dead = true;
                }
                if(b.get(j).die(y)) {
                    b.remove(0);
                    b.add(new Obstacle(roundRandom(1, 4), i));
                    i++;
                }
            }
        }
    }
    void drawRedObstacles(Canvas canvas) {
        for (int j = b.size() - 1; j >= 0; j--) {
            if (b.get(j).type == 1 || b.get(j).type == 2) {
                b.get(j).draw(canvas, y);
                if(b.get(j).collide(jet, y)) {
                    dead = true;
                }
                if(b.get(j).die(y)) {
                    b.remove(0);
                    b.add(new Obstacle(roundRandom(1, 4), i));
                    i++;
                }
            }
        }
    }
    void touchyStuff() {
        if(Touch.isTouching) {
            jet.height = "down";
            if (!dead) {
                jet.sca += (0.8 - jet.sca) / 8;
                jet.x2 += (-2.5 - jet.x2) / 10;
                jet.y2 += (-2.5 - jet.y2) / 8;
            }
        } else {
            jet.height = "up";
            if (!dead) {
                jet.sca += (1.2 - jet.sca) / 8;
                jet.x2 -= jet.x2/10;
                jet.y2 -= jet.y2/8;
            }
        }
    }
    void restartGame() {
        try {
            Intent i = context.getApplicationContext().getPackageManager()
                    .getLaunchIntentForPackage(context.getApplicationContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
