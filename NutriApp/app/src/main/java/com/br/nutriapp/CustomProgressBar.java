package com.br.nutriapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomProgressBar extends View {

    private final float stroke = 40.0f;
    private final RectF backgroundArc = new RectF();
    private final Paint bgPaint = new Paint();

    private final Paint paint = new Paint();
    private final Rect bounds = new Rect();
    private final RectF barArc = new RectF();
    private int progressBgColor;
    private int progressColor;
    private int progrresValue;

    public CustomProgressBar(Context context) {
        super(context);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);

        progrresValue = typedArray.getInt(R.styleable.CustomProgressBar_my_progress, 0);
        progressColor = typedArray.getColor(R.styleable.CustomProgressBar_my_progress_color, 0);
        progressBgColor = typedArray.getColor(R.styleable.CustomProgressBar_my_progress_bg_color, Color.LTGRAY);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float stroke = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.stroke, metrics);

        backgroundArc.set(stroke, stroke, getWidth() - stroke, getHeight() - stroke);

        bgPaint.setColor(progressBgColor);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(50.0f);
        bgPaint.setAntiAlias(true);

        canvas.drawArc(backgroundArc, 0.0f, 360.0f, false, bgPaint);

        float progress = (360.0f / 100) * progrresValue;
        canvas.getClipBounds(bounds);

        paint.setColor(progressColor);
        paint.setStrokeWidth(stroke - 40.0f);
        paint.setDither(true); // <- melhorar cor
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND); // <- deixar pontar redonda
        paint.setAntiAlias(true);

        barArc.set(stroke, stroke, bounds.right - stroke, bounds.bottom - stroke);

        canvas.drawArc(barArc, 270.0f, progress, false, paint);

    }
}
