package com.example.knjizara.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.R;
import com.example.knjizara.adapter.TopLevelRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.model.Korisnik;
import com.example.knjizara.viewmodel.KnjizaraInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class Tab1 extends Fragment {
    View rootView;
    KnjizaraInfo knjizaraInfo;
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    ArrayList<Knjiga> niz1 = new ArrayList<>();
    ArrayList<Knjiga> hronoloskaLista;
    Gson gson;
    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    public final String CHANNEL_ID = "PlacanjeActivity";
    public int NOTIFICATION_ID = 001;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        knjizaraInfo = new KnjizaraInfo(getActivity());
        niz0 = knjizaraInfo.getNiz(0);
        niz1 = knjizaraInfo.getNiz(1);
        hronoloskaLista = knjizaraInfo.mixBooks();

        pref = getActivity().getSharedPreferences("com.example.knjizara", getActivity().MODE_PRIVATE);
        prefsEditor = pref.edit();
        gson = new Gson();
        if(!isInitAppDataDone()) {
            initAppData();
            Log.i("MainActivity ","Setup appdata upravo gotov.");
        }
        else {
            Log.i("MainActivity ","Setup appdata uradjen ranije. ");
        }

        try {
            knjizaraInfo.unzipAll();
        } catch (IOException e) {
            e.printStackTrace();
        }


        LinearLayout najpKnjige = rootView.findViewById(R.id.najpopularnijeKnjige);
        initRecyclerView("popularne");
        initRecyclerView("besplatne");
        initRecyclerView("hronoloskaLista");
        return rootView;
    }

    public void initRecyclerView (String kat) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        if(kat == "popularne") {
            RecyclerView recyclerView = rootView.findViewById(R.id.najpopularnijeKnjige0);

            recyclerView.setLayoutManager(layoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(getActivity(),niz0);
            recyclerView.setAdapter(adapter);
        }
        else if (kat == "besplatne") {

            RecyclerView recyclerView = rootView.findViewById(R.id.besplatneKnjige0);
            recyclerView.setLayoutManager(layoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(getActivity(),niz1);
            recyclerView.setAdapter(adapter);
        }
        else if (kat == "hronoloskaLista") {

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
            RecyclerView recyclerView = rootView.findViewById(R.id.hronoloskaLista0);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(getActivity(),hronoloskaLista);
            adapter.setDuzina(hronoloskaLista.size());
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    }

    public void initAppData() {

        Korisnik korisnik = new Korisnik();
        ArrayList<Knjiga> korpa = new ArrayList<Knjiga>();
        ArrayList<Knjiga> mojeKnjige = new ArrayList<Knjiga>();

        String jsonKorisnik = gson.toJson(korisnik);
        String jsonKorpa = gson.toJson(korpa);
        String jsonMojeKnjige = gson.toJson(mojeKnjige);

        prefsEditor.putString("korisnik",jsonKorisnik);
        prefsEditor.putString("korpa",jsonKorpa);
        prefsEditor.putString("mojeKnjige",jsonMojeKnjige);


        prefsEditor.apply();

    }

    public boolean isInitAppDataDone () {
        if(pref.contains("korisnik") && pref.contains("korpa") && pref.contains("mojeKnjige")) {
            return true;
        }
        return false;
    }
}