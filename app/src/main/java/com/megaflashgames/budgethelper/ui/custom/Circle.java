package com.megaflashgames.budgethelper.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;

/**
 * Created by VanyaMihova on 8/6/2015.
 */
public class Circle extends TextView {

    private Paint fillPaint, borderPaint;
    private RectF fillRect, borderRect;

    int circleColor, borderColor;
    float backgroundSize;


    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.circleView, 0, 0);
        circleColor = typedArray.getColor(R.styleable.circleView_circleColor, Color.BLACK);
        borderColor = typedArray.getColor(R.styleable.circleView_borderColor, Color.WHITE);
        backgroundSize = typedArray.getFloat(R.styleable.circleView_backgroundSize, 0.5f);

        // create the Paint and set its color
        fillPaint = new Paint();
        borderPaint = new Paint();

        fillRect = new RectF();
        borderRect = new RectF();

        fillPaint.setColor(circleColor);
        borderPaint.setColor(borderColor);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        float hW = (w * backgroundSize) / 2f;
        float hH = (h * backgroundSize) / 2f;

        fillRect.set(hW, hH, w - hW, h - hH);

        borderRect.set(hW, hH, w - hW + 2.0f, h - hH + 2.0f);
        borderRect.offset(-1, -1);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawOval(borderRect, borderPaint);
        canvas.drawOval(fillRect, fillPaint);

//        canvas.drawCircle(202, 202, 100, borderPaint);
//        canvas.drawCircle(200, 200, 98, fillPaint);
    }


}
