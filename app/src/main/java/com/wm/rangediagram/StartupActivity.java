package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class StartupActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);


        Intent intent = new Intent(StartupActivity.this,InputRangeActivity.class);
        startActivity(intent);

    }

}
