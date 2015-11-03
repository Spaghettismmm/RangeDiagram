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

 */
public class Drawrangeview extends View {
    private float mTextWidth = 0.0f;
    private String drawLabel;
    private Paint drawPaint;
    private int labelColor, drawColor;
    private int HWx, HWy, PWx, PWy, SARx, SARy, Sx, Sy, DLR, TW,HWxo, HWyo, PWxo, PWyo, SARxo, SARyo, Sxo, Syo, Sxf,Syf,Sxfo,Syfo;
    private static final String LOG_TAG = "LOG Cat";
    private int w, h;
    private boolean myCalculationsAreReady=false;
    private boolean yes;
    Paint mPaint=new Paint();
    private Paint gridPaint, dlPaint, drawoldpitPaint;
    Context context;
    private RectF mRangeBounds = new RectF();
    private float totallength,totalheight,drawpadl,drawpadh,HWxp,HWyp, PWxp,PWyp,SARxp,SARyp,Sxp,Syp, DLRx1,DLRy1,DLRx2,DLRy2,textSize,textSize1, DLRX1p,DLRX2p,DLRY1p,DLRY2p;
    private float TWX1,TWX2,TWY1,TWY2, TWX1p,TWX2p,TWY1p,TWY2p,HWxpo,HWypo, PWxpo,PWypo,SARxpo,SARypo,Sxpo,Sypo, Areacutxp, Areacutyp, Areaspoilxp, Areaspoilyp;
    private float Sxfp,Syfp,Sxfpo,Syfpo;
    private double Pitarea, Spoilarea;
    private int gridintervaly = 20,gridintervalx = 50, dlheight = 150;
    private int ccount, grdcounty, grdcountx;
    private float[] gridpntsy, gridpntsx;
    private String[] gridlbly, gridlblx;
    private Drawrangeview mrangeview;
    public Drawrangeview(Context context) {

        this(context, null);
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

    public void setSides(int HWxi, int HWyi, int PWxi,int PWyi,int SARxi,int SARyi,int Sxi,int Syi,int Sxfi, int Syfi, Boolean yesi, int DLRi, int TWi,
                         int HWxio, int HWyio, int PWxio,int PWyio,int SARxio,int SARyio,int Sxio,int Syio, int Sxfio, int Syfio, double Pitareai, double Spoilareai) {
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
        Pitarea=Pitareai;
        Spoilarea=Spoilareai;

        DLR=DLRi;
        TW=TWi;

        HWxo =HWxio;
        HWyo=HWyio;
        PWxo=PWxio;
        PWyo=PWyio;
        SARxo=SARxio;
        SARyo=SARyio;
        Sxo = Sxio;
        Syo=Syio;


        Sxf=Sxfi;
        Syf=Syfi;
        Sxfo=Sxfio;
        Syfo=Syfio;




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
            drawpadl=20;
            totallength=Math.max(SARx,Math.max(PWx,Math.max(Sx,Math.max(SARxo,Math.max(PWxo,Math.max(Sxo,Math.max(HWx,HWxo)))))))+drawpadl*2;
            drawpadh=20;
            totalheight=Math.max(SARy,Math.max(PWy,Math.max(Sy,HWy))) + dlheight+drawpadh;
            //Move Drawing to padding distance


            //FOR DRAGLINE REACH AND TUB
            DLRx1=HWx-TW/2;
            DLRy1=HWy;
            DLRx2=DLRx1+DLR;
            DLRy2=HWy+dlheight;

            TWX1=HWx-TW;
            TWX2=HWx;
            TWY1=HWy;
            TWY2=HWy+10;

            //To Scale Drawing
            HWxp=w*(HWx/totallength)+drawpadl;
            HWyp=h-h*(HWy/totalheight)-drawpadh;
            PWxp=w*(PWx/totallength)+drawpadl;
            PWyp=h-h*(PWy/totalheight)-drawpadh;
            SARxp=w*(SARx/totallength)+drawpadl;
            SARyp=h-h*(SARy/totalheight)-drawpadh;
            Sxp=w*(Sx/totallength)+drawpadl;
            Syp=h-h*(Sy/totalheight)-drawpadh;

            HWxpo=w*((HWxo)/totallength)+drawpadl;
            HWypo=h-h*(HWyo/totalheight)-drawpadh;
            PWxpo=w*(PWxo/totallength)+drawpadl;
            PWypo=h-h*(PWyo/totalheight)-drawpadh;
            SARxpo=w*(SARxo/totallength)+drawpadl;
            SARypo=h-h*(SARyo/totalheight)-drawpadh;
            Sxpo=w*(Sxo/totallength)+drawpadl;
            Sypo=h-h*(Syo/totalheight)-drawpadh;


            DLRX1p=w*(DLRx1/totallength)+drawpadl;
            DLRX2p=w*(DLRx2/totallength)+drawpadl;
            DLRY1p=h-h*(DLRy1/totalheight)-drawpadh;
            DLRY2p=h-h*(DLRy2/totalheight)-drawpadh;

            TWX1p=w*(TWX1/totallength)+drawpadl;
            TWX2p=w*(TWX2/totallength)+drawpadl;
            TWY1p=h-h*(TWY1/totalheight)-drawpadh;
            TWY2p=h-h*(TWY2/totalheight)-drawpadh;

            Sxfp=w*((Sxf)/totallength)+drawpadl;
            Syfp=h-h*(Syf/totalheight)-drawpadh;
            Sxfpo=w*((Sxfo)/totallength)+drawpadl;
            Syfpo=h-h*(Syfo/totalheight)-drawpadh;


            Areacutxp=w*(((SARx/2))/totallength)+drawpadl;
            Areacutyp=h-h*((HWy/2)/totalheight)-drawpadh;
            Areaspoilxp=w*((Sx)/totallength)+drawpadl;
            Areaspoilyp=h-h*((Sy/2)/totalheight)-drawpadh;

            //For Grid

            grdcounty = Math.round(totalheight)/ gridintervaly;
            grdcountx = (int) Math.ceil(totallength / gridintervalx);
            gridpntsy = new float[4* grdcounty];
            gridpntsx= new float[4*grdcountx];
            gridlbly= new String[grdcounty];
            gridlblx= new String[grdcountx];

            //For y axis grid lines
            ccount=0;
              for (int i=0; i< grdcounty *4; i+=4)
              {
                  gridpntsy[i] = 0; //x1
                  gridpntsy[i + 1] = Math.round(h) - h * (gridintervaly *ccount) / totalheight-drawpadh; //y1
                  gridpntsy[i + 2] = w; //x2
                  gridpntsy[i + 3] = Math.round(h) - h * (gridintervaly * ccount) / totalheight-drawpadh; //y2

                  gridlbly[ccount]=String.valueOf(gridintervaly *ccount);
                  ccount++;
              }

            //For x axis grid lines
            ccount=0;

            for (int i=0; i< (grdcountx) *4; i+=4)
            {
                gridpntsx[i+3] = 0; //y2
                gridpntsx[i ] =w * (gridintervalx *ccount) / totallength+drawpadl; //x1
                gridpntsx[i + 1] = h; //y1
                gridpntsx[i + 2] =w * (gridintervalx * ccount) / totallength+drawpadl; //x2

                gridlblx[ccount]=String.valueOf(gridintervalx *ccount);
                ccount++;
            }
               // gridpntsx[grdcountx-3] = 0; //y2
               // gridpntsx[grdcountx-2 ] =w * (gridintervalx *(ccount+1)) / totallength+drawpadl; //x1
               // gridpntsx[grdcountx-1] = h; //y1
               // gridpntsx[grdcountx] =w * (gridintervalx * ccount) / totallength+drawpadl;
                //gridlblx[ccount+1]=String.valueOf(gridintervalx *(ccount+1)+gridintervalx);

        }

        protected void Drawmystuff(Canvas canvas) {
            Log.i(LOG_TAG, "Drawmystuff called");

            findpath();
            //Log.d("drawLines", "Number of points in gridpntsy = " + gridpntsy.length/4);
            //Log.d("drawLines", "points in gridpntsy :" + Arrays.toString(gridpntsy));
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
            path.lineTo(Sxfp, Syfp);

            Path oldpit = new Path();
            // start the path at the "origin"
            oldpit.moveTo(HWxp, HWyp); // origin
            // add a line for side A
            oldpit.lineTo(HWxpo, HWypo);
            // add a line for side B
            oldpit.lineTo(PWxpo, PWypo);
            // close the path to draw the hypotenuse
            oldpit.lineTo(SARxpo, SARypo);
            oldpit.lineTo(Sxpo, Sypo);
            oldpit.lineTo(Sxfpo, Syfpo);
 //           canvas.drawRect(r,drawPaint);
            canvas.drawLines(gridpntsy, gridPaint);


            int beta=0;
                for (int alpha=0; alpha<gridlbly.length; alpha++) {
                    canvas.drawText(gridlbly[alpha], gridpntsy[beta]+drawpadl, gridpntsy[beta+1],gridPaint);
                    beta+=4;
                }
            canvas.drawLines(gridpntsx, gridPaint);
            int gamma=0;
                for (int delta=0; delta<gridlblx.length; delta++) {
                    canvas.drawText(gridlblx[delta], gridpntsx[gamma], gridpntsx[gamma + 1] - drawpadh,gridPaint);
                    gamma += 4;
                }

            canvas.drawPath(path, drawPaint);
            canvas.drawPath(oldpit, drawoldpitPaint);
            Path dragline = new Path();
            // start the path at the "origin"
            dragline.moveTo(DLRX1p, DLRY1p); // origin
            // add a line for side A
            dragline.lineTo(DLRX1p, DLRY2p);
            // add a line for side B
            dragline.lineTo(DLRX2p, DLRY2p);
            canvas.drawPath(dragline, dlPaint);

            Path dragline1 = new Path();
            dragline1.moveTo(DLRX1p, DLRY1p);
            dragline1.lineTo(DLRX2p, DLRY2p);
            canvas.drawPath(dragline1, dlPaint);
            // close the path to draw the hypotenuse

            canvas.drawRect(TWX1p, TWY1p, TWX2p, TWY2p, dlPaint);

            canvas.drawText(String.valueOf(Math.round(Spoilarea)) + " ft^2", Areaspoilxp, Areaspoilyp, drawPaint);
            canvas.drawText(String.valueOf(Math.round(Pitarea))+" ft^2", Areacutxp, Areacutyp, drawPaint);
            Log.i(LOG_TAG, "Drawn'd");

        }

        private void initViews(Context context, AttributeSet attrs) {
        Log.i(LOG_TAG, "initViews called");

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
            drawPaint.setStrokeWidth(5);
            drawPaint.setPathEffect(null);
            int myColor=context.getResources().getColor(R.color.greenish);
            drawPaint.setColor(myColor);
            drawPaint.setStyle(Paint.Style.STROKE);
            textSize1 = drawPaint.getTextSize();
            drawPaint.setTextSize(textSize1 * 3);


        drawoldpitPaint = new Paint();
            drawoldpitPaint.setStrokeWidth(5);
            drawoldpitPaint.setPathEffect(null);
            drawoldpitPaint.setColor(Color.RED);
            drawoldpitPaint.setStyle(Paint.Style.STROKE);

        gridPaint = new Paint();
            gridPaint.setStrokeWidth(4);
        gridPaint.setColor(Color.GRAY);
        textSize = gridPaint.getTextSize();
            gridPaint.setTextSize(textSize * 3);



            dlPaint = new Paint();
            dlPaint.setStrokeWidth(4);
            dlPaint.setColor(Color.BLUE);
            dlPaint.setPathEffect(null);
            dlPaint.setStyle(Paint.Style.STROKE);
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
