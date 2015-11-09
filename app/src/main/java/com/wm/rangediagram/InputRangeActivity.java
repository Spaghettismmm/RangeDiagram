package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bomb Shit on 9/30/2015.
 */


public class InputRangeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";
    boolean juststarted;
    private int TWval,DLRval,DDval,DHval;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        juststarted=true;
    }

    public void sendPitdim(View ButtonCreatePit) {
        //Check form for entries
        //Convert input to String

        final EditText editSAR = (EditText) findViewById(R.id.SAReditView);
        //if (editSAR.getText().toString().equals(""))
         //   Toast.makeText(getApplicationContext(), "Need Value", Toast.LENGTH_LONG).show();
        final String SARval = editSAR.getText().toString();

        final EditText editHW = (EditText) findViewById(R.id.HWeditView);
        String HWval = editHW.getText().toString();


        final EditText editPW = (EditText) findViewById(R.id.PWeditView);
        String PWval = editPW.getText().toString();


        final EditText editBench = (EditText) findViewById(R.id.BenchwidthView);
        String Benchval = editBench.getText().toString();

        final EditText editBenchh = (EditText) findViewById(R.id.BenchheightView);
        String Benchhval = editBenchh.getText().toString();

        final EditText editSF = (EditText) findViewById(R.id.SFView);
        String SFval = editSF.getText().toString();

        if(juststarted!=true) {

            final EditText editDLreach = (EditText) findViewById(R.id.DLreach);
            String DLRval = editDLreach.getText().toString();

            final EditText editTubW = (EditText) findViewById(R.id.TubW);
            String TWval = editTubW.getText().toString();

            final EditText editDD = (EditText) findViewById(R.id.DigDView);
            String DDval = editDD.getText().toString();

            final EditText editDH = (EditText) findViewById(R.id.DumpHView);
            String DHval = editDH.getText().toString();



        }else {
            String DLRval ="300";
            String TWval ="80";
            String DDval="150";
            String DHval= "150";

        }

        //Convert string to parse int
       // int PWnum = Integer.parseInt(PWval);
        //int Benchnum = Integer.parseInt(Benchval);
        //int HWnum = Integer.parseInt(HWval);
       // int SARnum = Integer.parseInt(SARval);
       // int Benchheightnum = Integer.parseInt(Benchhval);
        //int DLReachnum = Integer.parseInt(DLRval);
      //  int TWnum = Integer.parseInt(TWval);

        Log.i(LOG_TAG, "Store Values");



        Intent draw = new Intent(InputRangeActivity.this, DrawRange.class);
        draw.putExtra("SAR", SARval);
        draw.putExtra("HW", HWval);
        draw.putExtra("PW", PWval);
        draw.putExtra("Benchw", Benchval);
        draw.putExtra("Benchh", Benchhval);
        draw.putExtra("DLR", DLRval);
        draw.putExtra("TW", TWval);
        draw.putExtra("DH", DDval);
        draw.putExtra("DD", DHval);
        draw.putExtra("Source",true);
        draw.putExtra("SF", SFval);

        startActivity(draw);
    }

}
