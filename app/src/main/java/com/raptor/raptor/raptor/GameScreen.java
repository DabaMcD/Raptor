package com.raptor.raptor.raptor;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen extends View {
    private Context context;
    private Jet jet;
    private ArrayList<Obstacle> b;
    private int i;
    boolean dead, start, replay;
    float y;
    private double speed;
    private double f4, f3, f;
    double f2;
    private int highscore;
    private float dieRectStrength;
    private ArrayList<Float> sc;
    private InitText initText;
    private DieText dieText;
    private ScoreText scoreText;
    private Paint paint;
    int score;
    private boolean savedScore;

    public GameScreen(Context context) {
        super(context);
    }
    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    void constructor(Context context) {
        this.context = context;
        i = 0;
        b = new ArrayList<>();
        dead = true;
        replay = false;
        y = 0f;
        jet = new Jet(Screen.width / 2, Screen.height * 5 / 6);
        f4 = 255d;
        initText = new InitText();
        dieText = new DieText();
        highscore = readScore();
        scoreText = new ScoreText(highscore);
        dieRectStrength = 0;
        start = false;
        f2 = 0d;
        f3 = 0d;
        f = 255d;
        savedScore = false;
        speed = 5;
        sc = new ArrayList<>();
        sc.add(20f);
        sc.add(Screen.height / 20f);
        sc.add(Screen.width / 20f);
        paint = new Paint();
        createJetAndFirstObstacles();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.rgb(235, 235, 235));
        canvas.drawPaint(paint);
        canvas.save();
        canvas.translate(0, jet.y);
        canvas.scale(1, (float) jet.scale);
        canvas.translate(0, -jet.y);
        drawBlackObstacles(canvas);
        canvas.restore();

        jet.draw(canvas);

        canvas.save();
        canvas.translate(0, jet.y);
        canvas.scale(1, (float) jet.scale);
        canvas.translate(0, -jet.y);
        drawRedObstacles(canvas);
        canvas.restore();

        if (!dead) {
            f4 -= f4 / 20d;
            y += speed;
            speed += 0.003d;
            if (speed > 13d) {
                speed = 13d;
            }
        }

        touchyStuff();

        initText.draw(canvas, (float) f, y);

        if(dead & start) {
            jet.height = "down";
            if(!savedScore) {
                score = Math.round(y / 150);
                writeScore(score);
                savedScore = true;
            }
            dieText.draw(canvas, (int) f2, (int) dieRectStrength);
            dieRectStrength += (150 - dieRectStrength) / 10f;
            sc.set(2, sc.get(2) + (Screen.width / 13f - sc.get(2)) / 10f);
            sc.set(1, sc.get(1) + (Screen.height / 8 * 5 - sc.get(1)) / 10f);
            sc.set(0, sc.get(0) + (((Screen.width - scoreText.halfTextWidth(y)) / 2f) - sc.get(0)) / 10f);
            f2 += (255d - f2) / 10d;
        }

        if(replay) {
            y -= y/10;
            f2 -= f2/10d;
            f3 -= f3/10d;
            sc.set(2, sc.get(2) + (15f - sc.get(2)) / 10f);
            sc.set(1, sc.get(1) + (10f - sc.get(1)) / 10f);
            sc.set(0, sc.get(0) + (35f - sc.get(0)) / 10f);
            f4 += (255d - f4) / 5d;
            if (y <= 1) {
                restartGame();
                dead = true;
                replay = false;
            }
        }

        if (start && !replay) {
            f3 += (255d - f3) / 10d;
            f -= f/10d;
        }

        scoreText.draw(canvas, sc, f3, f4, y);

        if (dead && !start && !replay) {
            f4 -= f4/20d;
        }

        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
    private void createJetAndFirstObstacles() {
        jet = new Jet(Screen.width / 2, Screen.height * 5 / 6);

        b.add(new Obstacle(roundRandom(3), i, jet));
        i = 1;
        b.add(new Obstacle(roundRandom(3), i, jet));
        i = 2;
        for (i = 2; -i * 250 > -Screen.height - 800; i++) {
            b.add(new Obstacle(roundRandom(4), i, jet));
        }
    }
    private int roundRandom(int max) {
        return (int) Math.round((Math.random() * (max - 1)) + 1);
    }
    private void drawBlackObstacles(Canvas canvas) {
        for (int j = b.size() - 1; j >= 0; j--) {
            if (b.get(j).type == 3 || b.get(j).type == 4) {
                b.get(j).draw(canvas, y);
                if(b.get(j).collide(jet, y)) {
                    dead = true;
                }
                if(b.get(j).die(y)) {
                    b.remove(0);
                    b.add(new Obstacle(roundRandom(4), i, jet));
                    i++;
                }
            }
        }
    }
    private void drawRedObstacles(Canvas canvas) {
        for (int j = b.size() - 1; j >= 0; j--) {
            if (b.get(j).type == 1 || b.get(j).type == 2) {
                b.get(j).draw(canvas, y);
                if(b.get(j).collide(jet, y)) {
                    dead = true;
                }
                if(b.get(j).die(y)) {
                    b.remove(0);
                    b.add(new Obstacle(roundRandom(4), i, jet));
                    i++;
                }
            }
        }
    }
    private void touchyStuff() {
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
    private void restartGame() {
        writeScore(Math.max(score, highscore));
        constructor(context);
    }
    private void writeScore(int score) {
        context.deleteFile("highscore.txt");
        File path = context.getFilesDir();
        File file = new File(path, "highscore.txt");
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(String.valueOf(score).getBytes());
            stream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private int readScore() {
        String text = "";
        FileInputStream fis;
        try {
            fis = context.openFileInput("highscore.txt");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        if(text.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(text);
        }
    }
}
