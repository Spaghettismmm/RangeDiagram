package com.wm.rangediagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class DrawRange extends Activity {
    private static final String LOG_TAG = "LOG Cat";
    private Paint mTextPaint;
    private float mTextHeight = 0.0f;
    private int mTextColor;
    private double HWx,HWy,SARx,SARy,PWx,PWy,Sx,Sy;
    private Drawrangeview myView;
    private String drawLabel;
    private Paint drawPaint;
    private int drawColor, labelColor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_canvas);
        Log.i(LOG_TAG, "Retrieve Values");
        /* Get values from Intent */

        //Convert input to String
        TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
        SARtext.setText(getIntent().getStringExtra("SAR"));
        String SARstring = SARtext.getText().toString();
        int SARnum = Integer.parseInt(SARstring);

        TextView HWtext = (TextView) findViewById(R.id.HWdrawview);
        HWtext.setText(getIntent().getStringExtra("HW"));
        String HWstring = HWtext.getText().toString();
        int HWnum = Integer.parseInt(HWstring);

        TextView PWtext = (TextView) findViewById(R.id.PWdrawview);
        PWtext.setText(getIntent().getStringExtra("PW"));
        String PWstring = PWtext.getText().toString();
        int PWnum = Integer.parseInt(PWstring);

        TextView BWtext = (TextView) findViewById(R.id.BWdrawview);
        BWtext.setText(getIntent().getStringExtra("Benchw"));
        String BWstring = BWtext.getText().toString();
        int BWnum = Integer.parseInt(BWstring);

        TextView BHtext = (TextView) findViewById(R.id.BHdrawview);
        BHtext.setText(getIntent().getStringExtra("Benchh"));
        String BHstring = BWtext.getText().toString();
        int BHnum = Integer.parseInt(BHstring);

        Log.i(LOG_TAG, "Retrieved");
        Log.d(LOG_TAG, "SAR: " + Float.toString(SARnum));
        Log.d(LOG_TAG, "HW: " + Float.toString(HWnum));
        Log.d(LOG_TAG, "Bench width: " + Float.toString(BWnum));
        Log.d(LOG_TAG, "PW: " + Float.toString(PWnum));
        Log.d(LOG_TAG, "Bench height: " + Float.toString(BHnum));

        docalcs(SARnum, HWnum, BWnum, PWnum, BHnum, 300, 70);

        Log.i(LOG_TAG, "Calculated");
        Log.d(LOG_TAG, "HWx: " + Double.toString(HWx));
        Log.d(LOG_TAG, "HWy: " + Double.toString(HWy));
        Log.d(LOG_TAG, "PWx; " + Double.toString(PWx));
        Log.d(LOG_TAG, "PWy; " + Double.toString(PWy));
        Log.d(LOG_TAG, "SARx: " + Double.toString(SARx));
        Log.d(LOG_TAG, "SARy: " + Double.toString(SARy));
        Log.d(LOG_TAG, "Sx; " + Double.toString(Sx));
        Log.d(LOG_TAG, "Sy; " + Double.toString(Sy));



        drawPaint = new Paint();

        drawPaint.setStrokeWidth(3);
        drawPaint.setPathEffect(null);
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);

        TypedArray a = obtainStyledAttributes(R.styleable.Drawrangeview);


        try {
        //get the text and colors specified using the names in attrs.xml
        drawLabel = a.getString(R.styleable.Drawrangeview_drawLabel);
        drawColor = a.getInteger(R.styleable.Drawrangeview_drawColor, 0);//0 is default
        labelColor = a.getInteger(R.styleable.Drawrangeview_labelColor, 0);
          } finally {
              a.recycle();
          }



        Drawrangeview rangeview = new Drawrangeview(this);
        float HWxi = (float) HWx;
        float HWyi=(float) HWy;
        float PWxi=(float) PWx;
        float PWyi=(float) PWy;
        float SARxi=(float) SARx;
        float SARyi=(float) SARy;
        float Sxi=(float) Sx;
        float Syi=(float) Sy;
        rangeview.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi);
        rangeview.invalidate();

    }
    private boolean mShowText;
    public boolean isShowText() {
        return mShowText;
    }


    public void docalcs(double Sar, double HW, double BW, double PW,double BH, double Reach, double Tub){
        HWx=BW*1.5;
        HWy=BH;
        double HWangle=HW*2*Math.PI/360.0;
        double SARangle=Sar*2*Math.PI/360.0;
        PWx=HWx+BH/(Math.tan(HWangle));
        PWy=HWy-BH;
        SARy=PWy;
        SARx=PWx+PW;
        Sx=SARx+(Reach-Tub/2-SARx);
        Sy=SARy+Sx*Math.tan(SARangle);





    }
    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backtoinput, menu);
        return super.onCreateOptionsMenu(menu);

    }
    // Process clicks on Options Menu items


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.InputScreen:
                Intent intent=new Intent(this,InputActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

