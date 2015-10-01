package com.wm.rangediagram;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_canvas);
        Log.i(LOG_TAG, "Retrieve Values");
        /* Get values from Intent */

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
        BWtext.setText(getIntent().getStringExtra("BW"));
        String BWstring = BWtext.getText().toString();
        int BWnum = Integer.parseInt(BWstring);


        Log.i(LOG_TAG, "Retrieved");
        Log.d(LOG_TAG, "SAR: " + Float.toString(SARnum));
        Log.d(LOG_TAG, "HW: " + Float.toString(HWnum));
        Log.d(LOG_TAG, "BW: " + Float.toString(BWnum));
        Log.d(LOG_TAG, "PW: " + Float.toString(PWnum));
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);


        if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);

        }



    }


    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_input, menu);
        return true;
    }

    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.input:


                Toast.makeText(getApplicationContext(), "Weeeeee",
                        Toast.LENGTH_SHORT).show();


                return true;
            default:
                return false;
        }
    }

}

