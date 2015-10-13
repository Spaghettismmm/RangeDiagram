package com.wm.rangediagram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
    public Drawrangeview(Context context) {

        super(context);
    }

    public Drawrangeview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
        //LayoutInflater.from(context).inflate(R.layout.key_value, this);
        }
    public Drawrangeview(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
        setWillNotDraw(false);
        }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(LOG_TAG, "onDraw called");
        Log.d(LOG_TAG, "HWx: " + Double.toString(HWx));
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
        Log.i(LOG_TAG, "Calc'd");
        if(yes==true){
        myCalculationsAreReady=true;}



    }

        private void drawDataNotReady(Canvas canvas) {
            canvas.drawText("Please wait while data is loading ...",1, canvas.getHeight()/2, mPaint);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            // Try for a width based on our minimum
            int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
            w = resolveSizeAndState(minw, widthMeasureSpec, 1);

            // Whatever the width ends up being, ask for a height that would let the triangle
            // get as big as it can
            int minh = MeasureSpec.getSize(w) - (int) mTextWidth + getPaddingBottom() + getPaddingTop();
            h = resolveSizeAndState(MeasureSpec.getSize(w) - (int) mTextWidth, heightMeasureSpec, 0);

            setMeasuredDimension(w, h);
        }
        //public void init(){
        // drawPaint = new Paint();

        // drawPaint.setStrokeWidth(3);
        // drawPaint.setPathEffect(null);
        // drawPaint.setColor(Color.BLACK);
        //drawPaint.setStyle(Paint.Style.STROKE);

        //TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.Drawrangeview,0,0);


        // try {
        //get the text and colors specified using the names in attrs.xml
        //    drawLabel = a.getString(R.styleable.Drawrangeview_drawLabel);
        //     drawColor = a.getInteger(R.styleable.Drawrangeview_drawColor, 0);//0 is default
        //     labelColor = a.getInteger(R.styleable.Drawrangeview_labelColor, 0);
        //  } finally {
        //      a.recycle();
        //  }
        // }


        protected void Drawmystuff(Canvas canvas){
            Log.i(LOG_TAG, "Drawmystuff called");

            Path path = new Path();
            // start the path at the "origin"
            path.moveTo(10, h/3); // origin
            // add a line for side A
            path.lineTo(this.HWx, this.HWy);
            // add a line for side B
            path.lineTo(this.PWx, this.PWy);
            // close the path to draw the hypotenuse
            path.lineTo(this.SARx, this.SARy);
            path.lineTo(this.Sx, this.Sy);

            canvas.drawPaint(drawPaint);
            canvas.drawPath(path, drawPaint);

            Log.i(LOG_TAG, "Drawn'd");

        }
    private void initViews(Context context, AttributeSet attrs) {
        Log.i(LOG_TAG, "initViews called");

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
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
    }



}
