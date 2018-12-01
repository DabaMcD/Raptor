package com.raptor.raptor.raptor;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameScreen extends View {
    private Paint paint;
    private Context context;

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
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
