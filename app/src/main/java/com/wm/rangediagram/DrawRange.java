package com.wm.rangediagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by Bomb Shit on 9/30/2015.
 */
public class DrawRange extends AppCompatActivity {
    Drawrangeview rangeview;

    private static final String LOG_TAG = "LOG Cat";
    private double HWx, HWy, SARx, SARy, PWx, PWy, Sx, Sy, HWxo, HWyo, PWxo, PWyo, SARxo, SARyo, Sxo, Syo, TOx, TOxo, SAReach, RHx, RHy;
    private double Syf, Sxf, Syfo, Sxfo, Sxftest,SFnum,SFtran, RHxo, RHyo;
    private double Pitarea, Spoilarea, bankspoilarea;
    private int TWtran, DLRtran, DHtran,DDtran,TOtran,SAtran, SARtran,HWtran ,PWtran,BWtran,BHtran, DLRnum,TWnum,DHnum,DDnum, SARnum,HWnum,BWnum, BHnum, PWnum, TOnum, SAnum;
    private boolean SARchange, yes, RHnum, RHtran;
    boolean juststarted;
    private boolean FromRangeInput;
    GridLayout parameterListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.i(LOG_TAG, "Retrieve Values");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        parameterListView=(GridLayout)findViewById(R.id.GridLayoutResults);

        registerForContextMenu(parameterListView);


        /* Get values from Intent */
        Intent intent =getIntent();
        juststarted=intent.getBooleanExtra("juststarted",true);
        FromRangeInput=intent.getBooleanExtra("FromRangeInput",true);
        grabdata();
    }

    public void grabdata() {
        //Convert input to String

        if (juststarted){
            SARchange=false;
            String SAstring = "90";
            SAnum = Integer.parseInt(SAstring);
            String DLRstring = "300";
            DLRnum = Integer.parseInt(DLRstring);
            String TWstring = "80";
            TWnum = Integer.parseInt(TWstring);
            String DHstring = "115";
            DHnum = Integer.parseInt(DHstring);
            String DDstring = "150";
            DDnum = Integer.parseInt(DDstring);
            String TOstring = "25";
            TOnum = Integer.parseInt(TOstring);

            Bundle extra = getIntent().getExtras();
            TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
            SARtext.setText(getIntent().getStringExtra("SAR"));
            String SARstring = SARtext.getText().toString();
            SARnum = Integer.parseInt(SARstring);

            TextView HWtext = (TextView) findViewById(R.id.HWdrawview);
            HWtext.setText(getIntent().getStringExtra("HW"));
            String HWstring = HWtext.getText().toString();
            HWnum = Integer.parseInt(HWstring);

            TextView PWtext = (TextView) findViewById(R.id.PWdrawview);
            PWtext.setText(getIntent().getStringExtra("PW"));
            String PWstring = PWtext.getText().toString();
            PWnum = Integer.parseInt(PWstring);

            TextView BWtext = (TextView) findViewById(R.id.BWdrawview);
            BWtext.setText(getIntent().getStringExtra("BW"));
            String BWstring = BWtext.getText().toString();
            BWnum = Integer.parseInt(BWstring);

            TextView BHtext = (TextView) findViewById(R.id.BHdrawview);
            BHtext.setText(getIntent().getStringExtra("BH"));
            String BHstring = BHtext.getText().toString();
            BHnum = Integer.parseInt(BHstring);

            String SFstring = extra.getString("SF");
            SFnum = Double.parseDouble(SFstring);

            Boolean RHbool = extra.getBoolean("RH");
            RHnum = RHbool;

        } else {

            if(FromRangeInput) {
                SARnum = SARtran;
                HWnum = HWtran;
                PWnum = PWtran;
                BWnum = BWtran;
                BHnum = BHtran;
                SFnum = SFtran;
                RHnum=RHtran;
                Intent extras = getIntent();
                extras.putExtra("SAR", SARnum);
                extras.putExtra("HW", HWnum);
                extras.putExtra("PW", PWnum);
                extras.putExtra("BW", BWnum);
                extras.putExtra("BH", BHnum);
                extras.putExtra("SF", SFnum);
                extras.putExtra("RH", RHnum);
            }else if(!FromRangeInput) {
                DLRnum = DLRtran;
                TWnum = TWtran;
                DHnum = DHtran;
                DDnum = DDtran;
                TOnum = TOtran;
                SAnum = SAtran;
                Intent extras = getIntent();
                extras.putExtra("DLR", DLRnum);
                extras.putExtra("SA", SAnum);
                extras.putExtra("TW", TWnum);
                extras.putExtra("DH", DHnum);
                extras.putExtra("DD", DDnum);
                extras.putExtra("TO", TOnum);

                Bundle extra = getIntent().getExtras();

                    if(extra == null) {
                        finish();
                    } else {
                        TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
                        SARtext.setText(getIntent().getStringExtra("SAR"));
                        String SARstring = SARtext.getText().toString();
                        SARnum = Integer.parseInt(SARstring);

                        TextView HWtext = (TextView) findViewById(R.id.HWdrawview);
                        HWtext.setText(getIntent().getStringExtra("HW"));
                        String HWstring = HWtext.getText().toString();
                        HWnum = Integer.parseInt(HWstring);

                        TextView PWtext = (TextView) findViewById(R.id.PWdrawview);
                        PWtext.setText(getIntent().getStringExtra("PW"));
                        String PWstring = PWtext.getText().toString();
                        PWnum = Integer.parseInt(PWstring);

                        TextView BWtext = (TextView) findViewById(R.id.BWdrawview);
                        BWtext.setText(getIntent().getStringExtra("BW"));
                        String BWstring = BWtext.getText().toString();
                        BWnum = Integer.parseInt(BWstring);

                        TextView BHtext = (TextView) findViewById(R.id.BHdrawview);
                        BHtext.setText(getIntent().getStringExtra("BH"));
                        String BHstring = BHtext.getText().toString();
                        BHnum = Integer.parseInt(BHstring);

                        String SFstring = extra.getString("SF");
                        SFnum = Double.parseDouble(SFstring);

                        Boolean RHbool = extra.getBoolean("RH");
                        RHnum = RHbool;
                    }
            }
        }

        Log.i(LOG_TAG, "Retrieved");


        if (RHnum){
            norehandlecalcs(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum);
            bestfitwrehandle(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum);
        }else {
            norehandlecalcs(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum);
        }
        Log.i(LOG_TAG, "Calculated");
        Log.d(LOG_TAG, "HWx: " + Double.toString(HWx));
        Log.d(LOG_TAG, "HWy: " + Double.toString(HWy));
        Log.d(LOG_TAG, "PWx; " + Double.toString(PWx));
        Log.d(LOG_TAG, "PWy; " + Double.toString(PWy));
        Log.d(LOG_TAG, "SARx: " + Double.toString(SARx));
        Log.d(LOG_TAG, "SARy: " + Double.toString(SARy));
        Log.d(LOG_TAG, "Sx; " + Double.toString(Sx));
        Log.d(LOG_TAG, "Sy; " + Double.toString(Sy));


        int HWxi = (int) HWx;
        int HWyi = (int) HWy;
        int PWxi = (int) PWx;
        int PWyi = (int) PWy;
        int SARxi = (int) SARx;
        int SARyi = (int) SARy;
        int Sxi = (int) Sx;
        int Syi = (int) Sy;
        int HWxio = (int) HWxo;
        int HWyio = (int) HWyo;
        int PWxio = (int) PWxo;
        int PWyio = (int) PWyo;
        int SARxio = (int) SARxo;
        int SARyio = (int) SARyo;
        int Sxio = (int) Sxo;
        int Syio = (int) Syo;
        int Sxfio = (int) Sxfo;
        int Syfio = (int) Syfo;
        int Sxfi = (int) Sxf;
        int Syfi = (int) Syf;
        int TOfi = (int) TOx;
        int RHxi = (int) RHx;
        int RHyi = (int) RHy;
        int RHxio = (int) RHxo;
        int RHyio = (int) RHyo;
        double SFi = SFnum;
        int SAi=SAnum;
        yes=true;

        if(SARchange){
            Intent extras = getIntent();
            extras.putExtra("SAR", SARnum);

            TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
            SARtext.setText(String.valueOf(SARnum));

        }

        rangeview = (Drawrangeview) findViewById(R.id.draw_canvas_main_activity);

        rangeview.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi, Sxfi, Syfi, yes, DLRnum, TWnum,
                HWxio, HWyio, PWxio, PWyio, SARxio, SARyio, Sxio, Syio, Sxfio, Syfio, Pitarea, Spoilarea, SFi, bankspoilarea, TOfi, SAi, SARchange, DHnum, RHxi, RHyi, RHxio, RHyio);
        //setContentView(rangeview);

    }

    public void bestfitwrehandle(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub, double DH, double DD, double SF, double TO, double SA) {
        double HWangle = Math.toRadians(HW);
        double SARangle = Math.toRadians(Sar);
        SAReach=Reach*(Math.sin(Math.toRadians(SA)));
        double Newreach = SAReach - (Tub / 2)- TO-(BH / (Math.tan(HWangle)));

        if(bankspoilarea<Pitarea){

            for (RHy=SARy; bankspoilarea<= Pitarea; RHy++) {

                RHx=RHy/Math.tan(HWangle);

                Sx = SARx-RHx + (Newreach);
                if ((SARy-RHy+(Newreach * (Math.tan(SARangle)))) < (HWy + DH)) {
                    Sy = SARy -RHy+ (Newreach * (Math.tan(SARangle)));
                } else {
                    Sy = HWy-RHy + DH;
                    SARangle=Math.atan((Sy-RHy)/Newreach);
                    SARnum=(int) Math.round(Math.toDegrees(SARangle));
                    SARchange=true;
                }
                if (Sxf > SARxo) {
                    bankspoilarea = (((.5 * (Sx - SARx) * Sy) - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy)) + ((.5 * (Sx - SARx) * Sy) - (Syf / Math.tan(SARangle) * Syf))) / SF;
                } else {
                    bankspoilarea = ((.5 * (Sx - SARx) * Sy) * 2 - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy)) / SF;
                }
                RHx=SARx-RHx;
            }

        }else{





        }


    }

    public void norehandlecalcs(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub, double DH, double DD, double SF, double TO, double SA) {
        HWx = BW;
        HWy = BH;
        double HWangle = Math.toRadians(HW);
        double SARangle = Math.toRadians(Sar);
        PWx = HWx + BH / (Math.tan(HWangle));
        PWy = HWy - BH;
        SARy = PWy;
        SARx = PWx + PW;
        TOx=TO;

        double Tubloc=BW+PW-TO-Tub/2;
        SAReach=Reach*(Math.sin(Math.toRadians(SA)));
        double Newreach = SAReach - (Tub / 2)- TO-(BH / (Math.tan(HWangle)));

        Sx = SARx + (Newreach);
        if ((SARy + (Newreach * (Math.tan(SARangle)))) < (HWy + DH)) {
            Sy = SARy + (Newreach * (Math.tan(SARangle)));
        } else {
            Sy = HWy + DH;
            SARangle=Math.atan(Sy/Newreach);
            SARnum=(int) Math.round(Math.toDegrees(SARangle));
            SARchange=true;
        }

        //for old pit
        HWxo = BW +PW;
        HWyo = BH;
        PWxo = HWxo + BH / (Math.tan(HWangle));
        PWyo = HWyo - BH;
        SARyo = PWyo;
        SARxo = PWxo + PW;
        Sxo = SARxo + (Newreach);
        if (SARyo + (Newreach * (Math.tan(SARangle))) < (HWyo + DH)) {
            Syo = SARyo + (Newreach * (Math.tan(SARangle)));
        } else {
            Syo = HWyo + DH;
        }
        //Calculate Intercept for Spoil Toe between old and new spoil
        Sxftest = (((Sx + Newreach) * Math.tan(SARangle) - (-Math.tan(SARangle) * SARxo)) / (2 * (Math.tan(SARangle))));

        if (Sxftest > SARxo) {
            Sxf = Sxftest;
            Syf = -Math.tan(SARangle) * (Sxf) + ((Sx + Newreach) * Math.tan(SARangle));
        } else {
            Sxf = Sx + ((Newreach));
            Syf = SARy;

        }

        Sxfo = SARxo + Sxf-SARx;
        Syfo = Syf;

        RHx=SARx;
        RHy=SARy;

        RHxo=SARxo;
        RHyo=SARyo;
        //Areas
        Pitarea = (SARx - PWx-2*(PWx - HWx)) * HWy + 2 * (.5 * HWy * (PWx - HWx));

        if (Sxf > SARxo) {
            Spoilarea = (((.5 * (Sx - SARx) * Sy) - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy)) + ((.5 * (Sx - SARx) * Sy) - (Syf / Math.tan(SARangle) * Syf)));
        } else {
            Spoilarea = ((.5 * (Sx - SARx) * Sy) * 2 - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy));
        }

        bankspoilarea=Spoilarea/SF;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.draglinedims:
                /*
                if (!juststarted) {
                    Bundle extras = getIntent().getExtras();
                    String SAstring = extras.getString("SA");
                    SAnum = Integer.parseInt(SAstring);

                    String DLRstring = extras.getString("DLR");
                    DLRnum = Integer.parseInt(DLRstring);

                    String TWstring = extras.getString("TW");
                    TWnum = Integer.parseInt(TWstring);

                    String DHstring = extras.getString("DH");
                    DHnum = Integer.parseInt(DHstring);

                    String DDstring = extras.getString("DD");
                    DDnum = Integer.parseInt(DDstring);

                    String TOstring = extras.getString("TO");
                    TOnum = Integer.parseInt(TOstring);
                }
                */
                    Intent dlsize = new Intent(this, InputDLSizeActivity.class);
                    dlsize.putExtra("SA", SAnum);
                    dlsize.putExtra("DLR", DLRnum);
                    dlsize.putExtra("TW", TWnum);
                    dlsize.putExtra("DH", DHnum);
                    dlsize.putExtra("DD", DDnum);
                    dlsize.putExtra("TO", TOnum);
                    startActivityForResult(dlsize, 1);


                return true;
            case R.id.listdimensions:
                openContextMenu(parameterListView);
                return true;
            case R.id.adjustrangesettings:
                Intent rangesize = new Intent(this, InputRangeActivity.class);
                rangesize.putExtra("SAR", SARnum);
                rangesize.putExtra("HW", HWnum);
                rangesize.putExtra("PW", PWnum);
                rangesize.putExtra("BW", BWnum);
                rangesize.putExtra("BH", BHnum);

                rangesize.putExtra("FromRangeInput",false);
                rangesize.putExtra("SF", SFnum);

                startActivityForResult(rangesize, 1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String SAstring,DLRstring,TWstring,DHstring,DDstring,TOstring,SARstring,SFstring,HWstring,BHstring,BWstring,PWstring;
        int SAnumretrieve,DLRnumretrieve,TWnumretrieve,DHnumretrieve,DDnumretrieve,TOnumretrieve, SARnumretrieve,HWnumretrieve,PWnumretrieve,BWnumretrieve,BHnumretrieve;
        double SFnumretrieve;
        if (resultCode == RESULT_OK) {

                Bundle extras = data.getExtras();

            FromRangeInput= extras.getBoolean("FromRangeInput");
                if(FromRangeInput) {
                    SARstring= extras.getString("SAR");
                    SARnumretrieve = Integer.parseInt(SARstring);

                    HWstring = extras.getString("HW");
                    HWnumretrieve = Integer.parseInt(HWstring);

                    PWstring = extras.getString("PW");
                    PWnumretrieve = Integer.parseInt(PWstring);

                    BWstring = extras.getString("BW");
                    BWnumretrieve = Integer.parseInt(BWstring);

                    BHstring = extras.getString("BH");
                    BHnumretrieve = Integer.parseInt(BHstring);

                    SFstring = extras.getString("SF");
                    SFnumretrieve = Double.parseDouble(SFstring);

                    Boolean RHboolretrieve = extras.getBoolean("RH");
                    RHnum = RHboolretrieve;

                    SARtran=SARnumretrieve;
                    HWtran=HWnumretrieve;
                    PWtran=PWnumretrieve;
                    BWtran=BWnumretrieve;
                    BHtran=BHnumretrieve;
                    SFtran=SFnumretrieve;
                    RHtran=RHboolretrieve;
                    juststarted=false;


                } else {
                    SAstring= extras.getString("SA");
                    SAnumretrieve = Integer.parseInt(SAstring);

                    DLRstring = extras.getString("DLR");
                    DLRnumretrieve = Integer.parseInt(DLRstring);

                    TWstring = extras.getString("TW");
                    TWnumretrieve = Integer.parseInt(TWstring);

                    DHstring = extras.getString("DH");
                    DHnumretrieve = Integer.parseInt(DHstring);

                    DDstring = extras.getString("DD");
                    DDnumretrieve = Integer.parseInt(DDstring);

                    TOstring = extras.getString("TO");
                    TOnumretrieve = Integer.parseInt(TOstring);

                    DLRtran=DLRnumretrieve;
                    TWtran=TWnumretrieve;
                    DHtran=DHnumretrieve;
                    DDtran=DDnumretrieve;
                    TOtran=TOnumretrieve;
                    SAtran=SAnumretrieve;

                    juststarted=false;
                }


            grabdata();
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Parameters and Volumes");
        menu.add(0, v.getId(), 0, "SAR " + SARnum + " deg");
        menu.add(0, v.getId(), 0, "HW @ " + HWnum + " deg");
        menu.add(0, v.getId(), 0, "PW " + PWnum + " ft");
        menu.add(0, v.getId(), 0, "BW " + BWnum + " ft");
        menu.add(0, v.getId(), 0, "BH " + BHnum + " ft");
        menu.add(0, v.getId(), 0, "SF " + SFnum + " ft");
        menu.add(0, v.getId(), 0, "Bank Pit Area " + Math.round(Pitarea) + " ft^2");
        menu.add(0, v.getId(), 0, "Loose Spoil Area " + Math.round(Spoilarea) + " ft");
        menu.add(0, v.getId(), 0, "Bank Spoil Area " + Math.round(bankspoilarea) + " ft");
        menu.add(0, v.getId(), 0, "HW Offset " + (PWx-HWx) + " ft");

    }
}


