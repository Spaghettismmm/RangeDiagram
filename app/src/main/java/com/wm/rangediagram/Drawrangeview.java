package com.wm.rangediagram;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
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
    private int labelColor, drawColor;
    private int HWx, HWy, PWx, PWy, SARx, SARy, Sx, Sy;
    private static final String LOG_TAG = "LOG Cat";
    private int w, h;
    private boolean myCalculationsAreReady=false;
    private boolean yes;
    Paint mPaint=new Paint();
    private Paint gridPaint;
    Context context;
    private RectF mRangeBounds = new RectF();
    private float totallength,totalheight,drawpadl,drawpadh,HWxp,HWyp, PWxp,PWyp,SARxp,SARyp,Sxp,Syp;
    private float[] gridpnts;

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

        invalidate();
        requestLayout();
    }

        private void drawDataNotReady(Canvas canvas) {
            canvas.drawText("Please wait while data is loading ...", 1, canvas.getHeight() / 2, mPaint);
        }

        @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Log.i(LOG_TAG, "onMeasure called");
            // Try for a width based on our minimum

            int minw=getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
            //w = resolveSize(minw, widthMeasureSpec);
            w=resolveSize(widthMeasureSpec,minw);

            int minh=getPaddingBottom() + getPaddingTop()+getSuggestedMinimumHeight();
            int canvasdim=resolveSize(MeasureSpec.getSize(R.layout.main_activity),minh);
            h = resolveSize(heightMeasureSpec, minh);

            setMeasuredDimension(w, h);

        }


        private void findpath(){
            Log.i(LOG_TAG,"findpath called");

            totallength=HWx+PWx+SARx+Sx;
            totalheight=Math.max(SARy,Math.max(PWy,Math.max(Sy,HWy))) + Math.min(SARy, Math.min(PWy, Math.min(Sy,HWy)));
            //Move Drawing to padding distance
            drawpadl=(w-totallength)/2;
            drawpadh=(h-totalheight)/2;

            //To Scale Drawing

            HWxp=w*(HWx/totallength);
            HWyp=h-h*(HWy/totalheight);
            PWxp=w*(PWx/totallength);
            PWyp=h-h*(PWy/totalheight);
            SARxp=w*(SARx/totallength);
            SARyp=h-h*(SARy/totalheight);
            Sxp=w*(Sx/totallength);
            Syp=h-h*(Sy/totalheight);


            //For Grid

            int gridinterval = 50;
            float[] gridpnts = new float[15];
            gridpnts[0]=0;
            gridpnts[1]=Math.round(h-drawpadh);
            gridpnts[2]=w;
            gridpnts[3]=Math.round(h-drawpadh);

            gridpnts[4]=0;
            gridpnts[5]=Math.round((h-drawpadh)-h*(gridinterval*2)/totalheight);
            gridpnts[6]=w;
            gridpnts[7]=(Math.round((h-drawpadh)-h*(gridinterval*2)/totalheight));

            gridpnts[8]=0;
            gridpnts[9]=(Math.round((h-drawpadh)-h*(gridinterval*3)/totalheight));
            gridpnts[10]=w;
            gridpnts[11]=(Math.round((h-drawpadh)-h*(gridinterval*3)/totalheight));

            gridpnts[12]=0;
            gridpnts[13]=(Math.round((h-drawpadh)-h*(gridinterval*4)/totalheight));
            gridpnts[14]=w;
            gridpnts[15]=(Math.round((h-drawpadh)-h*(gridinterval*4)/totalheight));




        }
        protected void Drawmystuff(Canvas canvas){
            Log.i(LOG_TAG, "Drawmystuff called");
            findpath();
            Rect r =new Rect(0,0,h,w);

            Path path = new Path();
            // start the path at the "origin"
            path.moveTo(drawpadl, HWyp); // origin
            // add a line for side A
            path.lineTo(HWxp, HWyp);
            // add a line for side B
            path.lineTo(PWxp, PWyp);
            // close the path to draw the hypotenuse
            path.lineTo(SARxp, SARyp);
            path.lineTo(Sxp, Syp);

 //           canvas.drawRect(r,drawPaint);
            canvas.drawLines(gridpnts, gridPaint);
            canvas.drawPaint(drawPaint);
            canvas.drawPath(path, drawPaint);


            Log.i(LOG_TAG, "Drawn'd");

        }
        private void initViews(Context context, AttributeSet attrs) {
        Log.i(LOG_TAG, "initViews called");
        //inflate(getContext(), R.layout.main_activity, this);
            //setWillNotDraw(false);
        TypedArray a = context.obtainStyledAttributes(R.styleable.Drawrangeview);


        try {

            //get the text and colors specified using the names in attrs.xml
            drawLabel = a.getString(R.styleable.Drawrangeview_drawLabel);

            drawColor = a.getColor(R.styleable.Drawrangeview_drawColor, 0);//0 is default
            labelColor = a.getColor(R.styleable.Drawrangeview_labelColor, Integer.MAX_VALUE);
        } finally {
            a.recycle();
        }

        drawPaint = new Paint();
        drawPaint.setStrokeWidth(10);
        drawPaint.setPathEffect(null);
            int myColor=context.getResources().getColor(R.color.greenish);
        drawPaint.setColor(myColor);
        drawPaint.setStyle(Paint.Style.STROKE);

        gridPaint = new Paint();
        gridPaint.setColor(Color.GRAY);



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



}
