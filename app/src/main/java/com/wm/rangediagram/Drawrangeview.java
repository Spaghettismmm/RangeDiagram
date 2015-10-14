package com.wm.rangediagram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Will on 10/4/2015.
 * http://stackoverflow.com/questions/14113177/android-create-a-triangle-based-on-user-inpu
 *
*
 * Created by xscz on 10/9/2015.
 */
public class Drawrangeview extends View {
    private float mTextWidth = 0.0f;
    private String drawLabel;
    private Paint drawPaint;
    private int drawColor, labelColor;
    private int HWx, HWy, PWx, PWy, SARx, SARy, Sx, Sy;
    private static final String LOG_TAG = "LOG Cat";
    private int w, h;
    private boolean myCalculationsAreReady=false;
    private boolean yes;
    Paint mPaint=new Paint();
    Context context;
    private RectF mRangeBounds = new RectF();
    private Drawrangeview mrangeview;
    public Drawrangeview(Context context) {

        this(context,null);
    }

    public Drawrangeview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        //LayoutInflater.from(context).inflate(R.layout.main_activity, this);
        }
    public Drawrangeview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i(LOG_TAG, "Constructor called");
        initViews(context, attrs);
        setWillNotDraw(false);
        }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(LOG_TAG, "onDraw called");
        if (myCalculationsAreReady) {

            Drawmystuff (canvas);

        } else {

            // You don't have your calculations yet , just ignore, or paint a message ...
            drawDataNotReady(canvas);

        }
    }
    public void setSides(int HWxi, int HWyi, int PWxi,int PWyi,int SARxi,int SARyi,int Sxi,int Syi,Boolean yesi) {
        Log.i(LOG_TAG, "setSides called");
        HWx =HWxi;
        HWy=HWyi;
        PWx=PWxi;
        PWy=PWyi;
        SARx=SARxi;
        SARy=SARyi;
        Sx = Sxi;
        Sy=Syi;
        yes=yesi;
        Log.i(LOG_TAG, "setSides done");
        if(yes==true){
        myCalculationsAreReady=true;}
        //setWillNotDraw(false);
        invalidate();
    }

        private void drawDataNotReady(Canvas canvas) {
            canvas.drawText("Please wait while data is loading ...", 1, canvas.getHeight() / 2, mPaint);
        }


        @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Log.i(LOG_TAG, "onMeasure called");
            // Try for a width based on our minimum

            int minw=HWx+PWx+SARx+Sx+getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
            //w = resolveSize(minw, widthMeasureSpec);
            w=Math.max(MeasureSpec.getSize(widthMeasureSpec),minw);

            int minh=MeasureSpec.getSize(w)+HWy+PWy+SARy+Sy+getPaddingBottom() + getPaddingTop();
            int canvasdim=resolveSize(MeasureSpec.getSize(R.layout.main_activity),minh);
            h = Math.min(MeasureSpec.getSize(heightMeasureSpec),canvasdim);

            setMeasuredDimension(w, h);
        }

        /*
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(LOG_TAG, "onSizeChanged called");
        //
        // Set dimensions for text, pie chart, etc
        //
        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        // Figure out how big we can make the pie.
        float diameter = Math.min(ww, hh);
        mRangeBounds = new RectF(
                0.0f,
                0.0f,
                diameter,
                diameter);
        mRangeBounds.offsetTo(getPaddingLeft(), getPaddingTop());
        // Lay out the child view that actually draws the pie.
        mrangeview.layout((int) mRangeBounds.left,
                (int) mRangeBounds.top,
                (int) mRangeBounds.right,
                (int) mRangeBounds.bottom);
        }
        */


        protected void Drawmystuff(Canvas canvas){
            Log.i(LOG_TAG, "Drawmystuff called");

            Path path = new Path();
            // start the path at the "origin"
            path.moveTo(0, h-this.HWy); // origin
            // add a line for side A
            path.lineTo(this.HWx, h-this.HWy);
            // add a line for side B
            path.lineTo(this.PWx, h-this.PWy);
            // close the path to draw the hypotenuse
            path.lineTo(this.SARx, h-this.SARy);
            path.lineTo(this.Sx, h-this.Sy);


            canvas.drawPaint(drawPaint);
            canvas.drawPath(path, drawPaint);

            Log.i(LOG_TAG, "Drawn'd");

        }
        private void initViews(Context context, AttributeSet attrs) {
        Log.i(LOG_TAG, "initViews called");
        //inflate(getContext(), R.layout.main_activity, this);

        TypedArray a = context.obtainStyledAttributes(R.styleable.Drawrangeview);


        try {

            //get the text and colors specified using the names in attrs.xml
            drawLabel = a.getString(R.styleable.Drawrangeview_drawLabel);
            drawColor = a.getInteger(R.styleable.Drawrangeview_drawColor, 0);//0 is default
            labelColor = a.getInteger(R.styleable.Drawrangeview_labelColor, 0);
        } finally {
            a.recycle();
        }

        drawPaint = new Paint();
        drawPaint.setStrokeWidth(3);
        drawPaint.setPathEffect(null);
        drawPaint.setColor(drawColor);
        drawPaint.setStyle(Paint.Style.STROKE);

    }



}
