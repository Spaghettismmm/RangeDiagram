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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        //Convert input to String
        final EditText editSAR = (EditText) findViewById(R.id.SAReditView);
        String SARval = editSAR.getText().toString();
        final EditText editHW = (EditText) findViewById(R.id.HWeditView);
        String HWval = editHW.getText().toString();
        final EditText editPW = (EditText) findViewById(R.id.PWeditView);
        String PWval = editPW.getText().toString();
        final EditText editBench = (EditText) findViewById(R.id.BenchwidthView);
        String Benchval = editBench.getText().toString();

        //Convert string to parse int
        int PWnum = Integer.parseInt(PWval);
        int Benchnum = Integer.parseInt(Benchval);
        int HWnum = Integer.parseInt(HWval);
        int SARnum = Integer.parseInt(SARval);

        Log.i(LOG_TAG, "Store Values");
        Log.d(LOG_TAG, "SAR: " + Float.toString(SARnum));
        Log.d(LOG_TAG, "HW: " + Float.toString(HWnum));
        Log.d(LOG_TAG, "PW: " + Float.toString(PWnum));
        Log.d(LOG_TAG, "Bench: " + Float.toString(Benchnum));

        Intent draw = new Intent(InputActivity.this,DrawRange.class);
        draw.putExtra("SAR",SARval);
        draw.putExtra("HW",HWval);
        draw.putExtra("PW",PWval);
        draw.putExtra("Bench",Benchval);


        startActivity(draw);
    }

}
