package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PopUpMojeKnjige extends AppCompatActivity {

    public TextView besplatneCount,placeneCount,ukupnoView;

public LinearLayout mainLayout;

    public MojeKnjigeSP mojeKnjigeSP;
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
        mojeKnjigeSP = new MojeKnjigeSP(this);
        besplatneCount = (TextView) findViewById(R.id.besplatneCount);
        placeneCount = (TextView) findViewById(R.id.placeneCount);
        ukupnoView = (TextView) findViewById(R.id.ukupno22);
        setViews();

    }
    public void setViews() {
        ArrayList<Knjiga> mojeKnjige = mojeKnjigeSP.getMojeKnjige();
        double ukupno = 0.0;
        int besplatne = 0;
        int placene = 0;
        for (Knjiga knjiga:mojeKnjige) {
            if(knjiga.getCena().startsWith("Bespl")) {
                besplatne++;
                continue;
            }
            else {
                ukupno += Double.parseDouble(knjiga.getCena());
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
