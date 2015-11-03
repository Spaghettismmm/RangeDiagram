package com.wm.rangediagram;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class DrawRange extends AppCompatActivity {
    Drawrangeview rangeview;

    private static final String LOG_TAG = "LOG Cat";
    private double HWx,HWy,SARx,SARy,PWx,PWy,Sx,Sy;
    private Boolean yes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.i(LOG_TAG, "Retrieve Values");

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar br = getSupportActionBar();

        br.setDisplayHomeAsUpEnabled(true);
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
        String BHstring = BHtext.getText().toString();
        int BHnum = Integer.parseInt(BHstring);

        TextView DLRtext = (TextView) findViewById(R.id.DLRdrawview);
        DLRtext.setText(getIntent().getStringExtra("DLR"));
        String DLRstring = DLRtext.getText().toString();
        int DLRnum = Integer.parseInt(DLRstring);

        TextView TWtext = (TextView) findViewById(R.id.TWdrawview);
        TWtext.setText(getIntent().getStringExtra("TW"));
        String TWstring = TWtext.getText().toString();
        int TWnum = Integer.parseInt(TWstring);

        TextView DHtext = (TextView) findViewById(R.id.DHdrawview);
        DHtext.setText(getIntent().getStringExtra("DH"));
        String DHstring = DHtext.getText().toString();
        int DHnum = Integer.parseInt(DHstring);

        TextView DDtext = (TextView) findViewById(R.id.DDdrawview);
        DDtext.setText(getIntent().getStringExtra("DD"));
        String DDstring = DDtext.getText().toString();
        int DDnum = Integer.parseInt(DDstring);

        Log.i(LOG_TAG, "Retrieved");

        docalcs(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum);

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

        rangeview =  (Drawrangeview) findViewById(R.id.draw_canvas_main_activity);

        rangeview.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi, yes, DLRnum, TWnum);
        //setContentView(rangeview);

    }


    public void docalcs(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub, double DD, double DH) {
        HWx = BW;
        HWy = BH;
        double HWangle = Math.toRadians(HW );
        double SARangle = Math.toRadians(Sar);
        PWx = HWx + BH / (Math.tan(HWangle));
        PWy = HWy - BH;
        SARy = PWy;
        SARx = PWx + PW;
        Sx = SARx + ((Reach-(Tub/2)-(SARx-HWx)));
        if (SARy+((Reach-(Tub/2)-(SARx-HWx)) * (Math.tan(SARangle)))>SARy+DH)
            {
                Sy=SARy+((Reach-(Tub/2)-(SARx-HWx)) * (Math.tan(SARangle)));
            }
        else{
                Sy=SARy+DH;
            }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.draglinedims:
                //
                return true;
            case R.id.listdimensions:
                //
                return true;
            case R.id.adjustrangesettings:
                //
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}




