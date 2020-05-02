package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {
    ObjectInputStream in;
    String [][] niz0 = new String[10][];
    String [][] niz1 = new String[10][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.otvoriZaCitanje();
        this.citanje();
        dodajKnjigeNaEkran("popularne");
        dodajKnjigeNaEkran("besplatne");


    }

    public void dodajKnjigeNaEkran(String kat) {

        ViewGroup mainlayout = (ViewGroup) findViewById(R.id.najpopularnijeKnjige0);

        System.out.println("NIZ0"+this.niz0.length);
        System.out.println("NIZ1"+this.niz1.length);

        if(kat == "popularne") {
            mainlayout = (ViewGroup) findViewById(R.id.najpopularnijeKnjige0);
        }
        else if(kat == "besplatne") {
            mainlayout = (ViewGroup) findViewById(R.id.besplatneKnjige0);
        }


        for (int i = 1;i<7;i++) {
            View inflatedView = View.inflate(this,R.layout.knjiga,mainlayout);
            inflatedView = null;
        }

        for (int j = 0;j<mainlayout.getChildCount();j++) {
            ViewGroup child = (ViewGroup) mainlayout.getChildAt(j);
            int brojElem = child.getChildCount();


            for (int k = 1;k<child.getChildCount();k++) {
                if (k==1) {
                    TextView naslovV = (TextView) child.getChildAt(k);
                    if(kat=="popularne"){
                        naslovV.setText(niz0[j][0]);
                    }
                    else {
                        naslovV.setText(niz1[j][0]);
                    }

                }
                else if(k==2) {
                    TextView autorV = (TextView) child.getChildAt(k);
                    if(kat=="popularne"){
                        autorV.setText(niz0[j][1]);
                    }
                    else {
                        autorV.setText(niz1[j][1]);
                    }
                }
                else if(k==3) {
                    TextView cenaV = (TextView) child.getChildAt(k);
                    if(kat=="popularne"){
                        cenaV.setText(niz0[j][2]);
                    }
                    else {
                        cenaV.setText(niz1[j][2]);
                    }
                }


            }
        }
        try {
            this.in.close();
        }
        catch (Exception e) {

        }
    }

    public void otvoriZaCitanje() {

        try {
            InputStream is = getAssets().open( "knjizaraInfo.out" );
//            InputStream is = getApplicationContext().getAssets().open("knjizaraInfo.out");
            this.in = new ObjectInputStream(
                    new BufferedInputStream(
                            is));
        }
        catch (Exception e) {
            System.out.println("Greska1:" + e.getMessage());
        }

    }

    public void citanje () {

        try {

            this.niz0 = (String [][]) (this.in.readObject());
            this.niz1 = (String [][]) (this.in.readObject());

            System.out.println("RADI");


        }
        catch (Exception e) {
            Log.i("Greska:",e.getMessage());
            try {
                this.in.close();
            }
            catch (Exception e2) {
                Log.i("Greska:",e2.getMessage());
            }

        }
    }

}
