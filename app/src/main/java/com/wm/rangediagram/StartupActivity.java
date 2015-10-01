package com.wm.rangediagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class StartupActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);


        Intent intent = new Intent(StartupActivity.this,InputActivity.class);
        startActivity(intent);

    }

}
