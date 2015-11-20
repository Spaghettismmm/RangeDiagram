package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bomb Shit on 9/30/2015.
 */


public class InputRangeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";
    boolean juststarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        Bundle extras = getIntent().getExtras();

        juststarted=extras.getBoolean("juststarted");

        if(!juststarted) {
            ActionBar br = getSupportActionBar();

            br.setDisplayHomeAsUpEnabled(true);
            Intent intent = getIntent();

            EditText SAReditview= (EditText) findViewById(R.id.SAReditView);
            SAReditview.setText(Integer.toString((intent.getIntExtra("SAR",0))));

            EditText HWeditview= (EditText) findViewById(R.id.HWeditView);
            HWeditview.setText(Integer.toString((intent.getIntExtra("HW",0))));

            EditText PWeditview= (EditText) findViewById(R.id.PWeditView);
            PWeditview.setText(Integer.toString((intent.getIntExtra("PW", 0))));

            EditText BWeditview= (EditText) findViewById(R.id.BenchwidthView);
            BWeditview.setText(Integer.toString((intent.getIntExtra("BW", 0))));

            EditText BHeditview= (EditText) findViewById(R.id.BenchheightView);
            BHeditview.setText(Integer.toString((intent.getIntExtra("BH", 0))));

            EditText SFeditview= (EditText) findViewById(R.id.SFeditView);
            SFeditview.setText(Double.toString((intent.getDoubleExtra("SF", 0.0))));


        }



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

        final EditText editSF = (EditText) findViewById(R.id.SFeditView);
        String SFval = editSF.getText().toString();

        if(juststarted) {
            juststarted=true;
        }

        Log.i(LOG_TAG, "Store Values");

        Intent draw = new Intent(InputRangeActivity.this, DrawRange.class);
        draw.putExtra("SAR", SARval);
        draw.putExtra("HW", HWval);
        draw.putExtra("PW", PWval);
        draw.putExtra("BW", Benchval);
        draw.putExtra("BH", Benchhval);
        draw.putExtra("FromRangeInput",true);
        draw.putExtra("juststarted", juststarted);
        draw.putExtra("SF", SFval);

        if(!juststarted) {
                setResult(RESULT_OK, draw);
                finish();
            } else {

                startActivity(draw);

        }
    }
}


