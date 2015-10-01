package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Bomb Shit on 9/30/2015.
 */


public class InputActivity extends Activity{
    private static final String LOG_TAG = "LOG Cat";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

    }

    public void sendPitdim(View ButtonCreatePit){

        final EditText editSAR = (EditText) findViewById(R.id.SAReditView);
        String SARval = editSAR.getText().toString();
        int SARnum = Integer.parseInt(SARval);

        final EditText editHW = (EditText) findViewById(R.id.HWeditView);
        String HWval = editHW.getText().toString();
        int HWnum = Integer.parseInt(HWval);

        final EditText editPW = (EditText) findViewById(R.id.PWeditView);
        String PWval = editPW.getText().toString();
        int PWnum = Integer.parseInt(PWval);

        final EditText editBench = (EditText) findViewById(R.id.BenchwidthView);
        String Benchval = editBench.getText().toString();
        int Benchnum = Integer.parseInt(Benchval);


        Log.i(LOG_TAG, "Store Values");
        Log.d(LOG_TAG, "SAR: " + Float.toString(SARnum));
        Log.d(LOG_TAG, "HW: " + Float.toString(HWnum));
        Log.d(LOG_TAG,"PW: " + Float.toString(PWnum));
        Log.d(LOG_TAG,"Bench: " + Float.toString(Benchnum));

        Intent draw = new Intent(InputActivity.this,DrawRange.class);
        draw.putExtra("SAR",SARval);
        draw.putExtra("HW",HWval);
        draw.putExtra("PW",PWval);
        draw.putExtra("Bench",Benchval);

        startActivity(draw);
    }
}
