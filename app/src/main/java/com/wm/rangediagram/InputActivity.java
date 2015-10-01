package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class InputActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

    }
    public void sendPitdim(View ButtonCreatePit){

        final EditText editSAR = (EditText) findViewById(R.id.SAReditView);
        String SAR = editSAR.getText().toString();

        final EditText editHW = (EditText) findViewById(R.id.HWeditView);
        String HW = editHW.getText().toString();

        final EditText editPW = (EditText) findViewById(R.id.PWeditView);
        String PW = editPW.getText().toString();

        final EditText editBench = (EditText) findViewById(R.id.BenchwidthView);
        String Bench = editBench.getText().toString();


        Intent draw = new Intent(InputActivity.this,DrawRange.class);
        startActivity(draw);
    }
}
