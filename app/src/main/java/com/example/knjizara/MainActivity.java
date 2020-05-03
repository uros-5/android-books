package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static android.view.View.MeasureSpec.makeMeasureSpec;

public class MainActivity extends AppCompatActivity {
    ObjectInputStream in;
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    ArrayList<Knjiga> niz1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.otvoriZaCitanje();
        this.citanje();
        initRecyclerView("popularne");
        initRecyclerView("besplatne");

        try {
            this.in.close();
        } catch (Exception e) {

        }


    }


    public void otvoriZaCitanje() {

        try {
            InputStream is = getAssets().open("knjizaraInfo.out");
//            InputStream is = getApplicationContext().getAssets().open("knjizaraInfo.out");
            this.in = new ObjectInputStream(
                    new BufferedInputStream(
                            is));
        } catch (Exception e) {
            System.out.println("Greska1:" + e.getMessage());
        }

    }

    public void citanje() {

        try {
            String[][] temp0 = (String[][]) (this.in.readObject());
            String[][] temp1 = (String[][]) (this.in.readObject());

            for (int i = 0; i < temp0.length; i++) {

                Knjiga knjiga = new Knjiga(temp0[i][0], temp0[i][1], temp0[i][2]);
                niz0.add(knjiga);
            }
            for (int i = 0; i < temp1.length; i++) {
                Knjiga knjiga = new Knjiga(temp1[i][0], temp1[i][1], temp1[i][2]);
                niz1.add(knjiga);
            }
            temp0 = null;
            temp1 = null;


        } catch (Exception e) {
            Log.i("Greska:", e.getMessage());
            try {
                this.in.close();
            } catch (Exception e2) {
                Log.i("Greska:", e2.getMessage());
            }

        }
    }
    public void initRecyclerView (String kat) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        if(kat == "popularne") {
            RecyclerView recyclerView = findViewById(R.id.najpopularnijeKnjige0);

            recyclerView.setLayoutManager(layoutManager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,niz0,niz1);
            recyclerView.setAdapter(adapter);
        }
        else if (kat == "besplatne") {

            RecyclerView recyclerView = findViewById(R.id.besplatneKnjige0);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,niz0,niz1);
            recyclerView.setAdapter(adapter);
        }




    }
}

