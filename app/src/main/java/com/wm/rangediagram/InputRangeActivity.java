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
    private String TWval,DLRval,DDval,DHval, TOval;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Intent intent =getIntent();
        juststarted=intent.getBooleanExtra("juststarted",true);
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

            final EditText editDLreach = (EditText) findViewById(R.id.DLreachview);
            DLRval = editDLreach.getText().toString();

            final EditText editTubW = (EditText) findViewById(R.id.TubWview);
            TWval = editTubW.getText().toString();

            final EditText editDD = (EditText) findViewById(R.id.DigDView);
            DDval = editDD.getText().toString();

            final EditText editDH = (EditText) findViewById(R.id.DumpHView);
            DHval = editDH.getText().toString();

            final EditText editTO = (EditText) findViewById(R.id.TubOffview);
            TOval = editTO.getText().toString();

        }else {
            DLRval ="300";
            TWval ="80";
            DDval="150";
            DHval= "150";
            TOval= "25";
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
        draw.putExtra("DD", DDval);
        draw.putExtra("DH", DHval);
        draw.putExtra("Source",true);
        draw.putExtra("SF", SFval);
        draw.putExtra("TO", TOval);
        startActivity(draw);
    }

}
