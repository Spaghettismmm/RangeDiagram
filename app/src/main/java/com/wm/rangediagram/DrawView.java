package com.wm.rangediagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.renderscript.ScriptGroup;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * Created by XSCZ on 10/4/2015.
 */

//http://stackoverflow.com/questions/14113177/android-create-a-triangle-based-on-user-input


class Drawrangeview extends View {
    private float mTextWidth = 0.0f;
    private float HWx, HWy, PWx, PWy, SARx, SARy, Sx, Sy;

    public Drawrangeview(Context ctx) {
        this(ctx, null);
    }

    public Drawrangeview(Context ctx, AttributeSet attrs) {
        this(ctx, attrs, 0);
    }


    public Drawrangeview(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }


    public void setSides(float HWx, float HWy, float PWx,float PWy,float SARx,float SARy,float Sx,float Sy) {
        this.HWx =HWx;
        this.HWy=HWy;
        this.PWx=PWx;
        this.PWy=PWy;
        this.SARx=SARx;
        this.SARy=SARy;
        this.Sx=Sx;
        this.Sy=Sy;
        this.invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the triangle
        // get as big as it can
        int minh = MeasureSpec.getSize(w) - (int) mTextWidth + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int) mTextWidth, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Paint paint = new Paint();
        Path path = new Path();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);

        canvas.drawPaint(paint);

        // start the path at the "origin"
        path.moveTo(10, 10); // origin
        // add a line for side A
        path.lineTo(HWx,HWy);
        // add a line for side B
        path.lineTo(PWx,PWy);
        // close the path to draw the hypotenuse
        path.lineTo(SARx,SARy);
        path.lineTo(Sx,Sy);


        paint.setStrokeWidth(3);
        paint.setPathEffect(null);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(path, paint);
    }
}