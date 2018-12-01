package com.raptor.raptor.raptor;


import android.content.Context;
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
        initializeGame();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
    void initializeGame() {
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
}
