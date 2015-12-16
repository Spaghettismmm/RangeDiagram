package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by WM on 11/9/2015.
 */
public class InputDLSizeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlsize_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar br = getSupportActionBar();

        br.setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            Intent dldrawsettings = new Intent(this, DrawRange.class);

            dldrawsettings.putExtra("FromRangeInput", false);
            dldrawsettings.putExtra("juststarted", true);
            setResult(RESULT_CANCELED, dldrawsettings);
            finish();
        } else {

            Intent intent = getIntent();

            EditText SAeditview = (EditText) findViewById(R.id.Swingangleview);
            SAeditview.setText(Integer.toString((intent.getIntExtra("SA", 0))));

            EditText DLReditview = (EditText) findViewById(R.id.DLreachview);
            DLReditview.setText(Integer.toString((intent.getIntExtra("DLR", 0))));


            EditText TWeditview = (EditText) findViewById(R.id.TubWview);
            TWeditview.setText(Integer.toString((intent.getIntExtra("TW", 0))));


            EditText DHeditview = (EditText) findViewById(R.id.DumpHView);
            DHeditview.setText(Integer.toString((intent.getIntExtra("DH", 0))));


            // EditText DDeditview = (EditText) findViewById(R.id.DigDView);
            // DDeditview.setText(Integer.toString((intent.getIntExtra("DD", 0))));

            EditText TOeditview = (EditText) findViewById(R.id.TubOffview);
            TOeditview.setText(Integer.toString((intent.getIntExtra("TO", 0))));
        }

    }


    public void DLDim_onClick(View dlsize_button) {

        final EditText editSA = (EditText) findViewById(R.id.Swingangleview);
        String SAval = editSA.getText().toString();
        if (SAval.matches("")) {
            Toast.makeText(this, "Enter a Swing Angle", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editDLreach = (EditText) findViewById(R.id.DLreachview);
        String DLRval = editDLreach.getText().toString();
        if (DLRval.matches("")) {
            Toast.makeText(this, "Enter a DL Reach", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editTubW = (EditText) findViewById(R.id.TubWview);
        String TWval = editTubW.getText().toString();
        if (TWval.matches("")) {
            Toast.makeText(this, "Enter a Tub Width", Toast.LENGTH_SHORT).show();
            return;
        }
//            final EditText editDD = (EditText) findViewById(R.id.DigDView);
//            String DDval = editDD.getText().toString();
//                if(DDval.matches("")){Toast.makeText(this,"Enter a Dig Depth",Toast.LENGTH_SHORT).show();return;}
        final EditText editDH = (EditText) findViewById(R.id.DumpHView);
        String DHval = editDH.getText().toString();
        if (DHval.matches("")) {
            Toast.makeText(this, "Enter a Dump Height", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editTO = (EditText) findViewById(R.id.TubOffview);
        String TOval = editTO.getText().toString();
        if (TOval.matches("")) {
            Toast.makeText(this, "Enter a Tub offset", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i(LOG_TAG, "Store Values dl settings");


        Intent dldrawsettings = new Intent(this, DrawRange.class);

        dldrawsettings.putExtra("SA", SAval);
        dldrawsettings.putExtra("DLR", DLRval);
        dldrawsettings.putExtra("TW", TWval);
        dldrawsettings.putExtra("DH", DHval);
//            dldrawsettings.putExtra("DD", DDval);
        dldrawsettings.putExtra("TO", TOval);
        dldrawsettings.putExtra("Source", false);
        dldrawsettings.putExtra("juststarted", false);
        dldrawsettings.putExtra("FromRangeInput", false);
        setResult(RESULT_OK, dldrawsettings);
        finish();



    }
}
