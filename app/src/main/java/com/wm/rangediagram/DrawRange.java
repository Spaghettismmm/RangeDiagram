package com.wm.rangediagram;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class DrawRange extends Activity {
    private static final String LOG_TAG = "LOG Cat";
    private Paint mTextPaint;
    private float mTextHeight = 0.0f;
    private int mTextColor;
    private RelativeLayout containerLayout;
    private double HWx,HWy,SARx,SARy,PWx,PWy,Sx,Sy;
    private Boolean yes;

    private String drawLabel;
    private Paint drawPaint;
    private int drawColor, labelColor;

    private boolean mShowText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_value);
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


        int HWxi = (int) HWx;
        int HWyi = (int) HWy;
        int PWxi = (int) PWx;
        int PWyi = (int) PWy;
        int SARxi = (int) SARx;
        int SARyi = (int) SARy;
        int Sxi = (int) Sx;
        int Syi = (int) Sy;

        yes=true;

        Drawrangeview rangeview = new Drawrangeview(DrawRange.this);

        rangeview.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi, yes);

//        Drawrangeview rangeview1 = (Drawrangeview) findViewById(R.id.drawcanvas);
//        rangeview1.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi,yes);
        rangeview.invalidate();
    }


    public void docalcs(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub) {
        HWx = BW * 1.5;
        HWy = BH;
        double HWangle = HW * 2 * Math.PI / 360.0;
        double SARangle = Sar * 2 * Math.PI / 360.0;
        PWx = HWx + BH / (Math.tan(HWangle));
        PWy = HWy - BH;
        SARy = PWy;
        SARx = PWx + PW;
        Sx = SARx + (Reach - Tub / 2 - SARx);
        Sy = SARy + Sx * Math.tan(SARangle);
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
                Intent intent = new Intent(this, InputActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}




