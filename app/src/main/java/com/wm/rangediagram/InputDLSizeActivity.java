package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        EditText DLReditview= (EditText) findViewById(R.id.DLreachview);
        DLReditview.setText(Integer.toString((intent.getIntExtra("DLR",0))));


        EditText TWeditview= (EditText) findViewById(R.id.TubWview);
        TWeditview.setText(Integer.toString((intent.getIntExtra("TW", 0))));


        EditText DHeditview= (EditText) findViewById(R.id.DumpHView);
        DHeditview.setText(Integer.toString((intent.getIntExtra("DH", 0))));


        EditText DDeditview= (EditText) findViewById(R.id.DigDView);
        DDeditview.setText(Integer.toString((intent.getIntExtra("DD", 0))));

        EditText TOeditview= (EditText) findViewById(R.id.TubOffview);
        TOeditview.setText(Integer.toString((intent.getIntExtra("TO", 0))));
    }


    public void DLDim_onClick(View dlsize_button) {

        final EditText editDLreach = (EditText) findViewById(R.id.DLreachview);
        String DLRval = editDLreach.getText().toString();

        final EditText editTubW = (EditText) findViewById(R.id.TubWview);
        String TWval = editTubW.getText().toString();

        final EditText editDD = (EditText) findViewById(R.id.DigDView);
        String DDval = editDD.getText().toString();

        final EditText editDH = (EditText) findViewById(R.id.DumpHView);
        String DHval = editDH.getText().toString();

        final EditText editTO = (EditText) findViewById(R.id.TubOffview);
        String TOval = editTO.getText().toString();


        Log.i(LOG_TAG, "Store Values dl settings");


        Intent dldrawsettings = new Intent(this, DrawRange.class);

        dldrawsettings.putExtra("DLR", DLRval);
        dldrawsettings.putExtra("TW", TWval);
        dldrawsettings.putExtra("DH", DDval);
        dldrawsettings.putExtra("DD", DHval);
        dldrawsettings.putExtra("TO", TOval);
        dldrawsettings.putExtra("Source", false);
        setResult(RESULT_OK, dldrawsettings);
        finish();
    }

}
