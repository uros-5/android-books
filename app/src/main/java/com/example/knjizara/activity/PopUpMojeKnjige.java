package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PopUpMojeKnjige extends AppCompatActivity {

    public TextView besplatneCount,placeneCount,ukupnoView;

public LinearLayout mainLayout;

    public MojeKnjigeSP mojeKnjigeSP;

    public JSONArray lista;
    public Klijent klijent;
    public KorisnikSP korisnikSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_moje_knjige);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.7));

        mainLayout = (LinearLayout) findViewById(R.id.mojeKnjigePopUp);
        besplatneCount = (TextView) findViewById(R.id.besplatneCount);
        placeneCount = (TextView) findViewById(R.id.placeneCount);
        ukupnoView = (TextView) findViewById(R.id.ukupno22);
        klijent = new Klijent();
        korisnikSP = new KorisnikSP(this);
        if(korisnikSP.isNull())
            setViews();
        else
            Toast.makeText(this,"Ulogujte se",Toast.LENGTH_LONG).show();
    }
    public void setViews() {
        //upit za moje knjige

//        ArrayList<Knjiga> mojeKnjige = mojeKnjigeSP.getMojeKnjige();
        lista = klijent.sendM("mojeKnjige "+korisnikSP.getKorisnik().toString());


        double ukupno = 0.0;
        int besplatne = 0;
        int placene = 0;

        for(int j=0;j<lista.length();j++) {
            String cena = null;
            try {
                cena = String.valueOf(lista.getJSONArray(j).get(5)).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
//                    System.out.println(jArray.getJSONArray(0).get(j));
            if(cena.startsWith("0")) {
                besplatne++;
                continue;
            }
            else {
                ukupno += Double.parseDouble(cena);
                placene++;
            }
        }
        besplatneCount.setText(String.valueOf(besplatne));
        placeneCount.setText(String.valueOf(placene));
        DecimalFormat df = new DecimalFormat("0.00");
        double dfUkupno = Double.parseDouble(df.format(ukupno));
        ukupnoView.setText(String.valueOf(dfUkupno));
        setOnClickListener();

    }

    public void setOnClickListener () {
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onBackBtn(View view) {
        onBackPressed();
    }
}
