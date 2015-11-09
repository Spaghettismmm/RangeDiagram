package com.wm.rangediagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by xscz on 11/9/2015.
 */
public class InputDLSizeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlsize_activity);

        Intent intent = getIntent();

        EditText DLReditview= (EditText) findViewById(R.id.DLreach);
        DLReditview.setText(Integer.toString((intent.getIntExtra("DLR",300))));


        EditText TWeditview= (EditText) findViewById(R.id.TubW);
        TWeditview.setText(Integer.toString((intent.getIntExtra("TW", 80))));


        EditText DHeditview= (EditText) findViewById(R.id.DumpHView);
        DHeditview.setText(Integer.toString((intent.getIntExtra("DH", 150))));


        EditText DDeditview= (EditText) findViewById(R.id.DigDView);
        DDeditview.setText(Integer.toString((intent.getIntExtra("DD", 150))));


    }


    public void DLDim_onClick(View dlsize_button) {

        final EditText editDLreach = (EditText) findViewById(R.id.DLreach);
        String DLRval = editDLreach.getText().toString();

        final EditText editTubW = (EditText) findViewById(R.id.TubW);
        String TWval = editTubW.getText().toString();

        final EditText editDD = (EditText) findViewById(R.id.DigDView);
        String DDval = editDD.getText().toString();

        final EditText editDH = (EditText) findViewById(R.id.DumpHView);
        String DHval = editDH.getText().toString();


        Log.i(LOG_TAG, "Store Values dl settings");


        Intent dldrawsettings = new Intent(this, DrawRange.class);

        dldrawsettings.putExtra("DLR", DLRval);
        dldrawsettings.putExtra("TW", TWval);
        dldrawsettings.putExtra("DH", DDval);
        dldrawsettings.putExtra("DD", DHval);
        dldrawsettings.putExtra("Source", false);
        setResult(RESULT_OK, dldrawsettings);
        finish();
    }

}
