package com.example.knjizara.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.adapter.MojeKnjigeRVAdapter;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.ListenerMojeKnjige;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

import java.util.ArrayList;


public class Tab2 extends Fragment {
    View rootView;
    public ListenerMojeKnjige listenerMojeKnjige;
    GridLayoutManager gridLayoutManager;
    public RecyclerView recyclerView;
    public MojeKnjigeRVAdapter adapter;
    Klijent klijent;
    KorisnikSP korisnikSP;
    ArrayList<ArrayList> lista;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
//        mojeKnjigeSP = new MojeKnjigeSP(getActivity());
        korisnikSP = new KorisnikSP(getActivity());
        klijent = new Klijent();
        initRecyclerView();
        cancelNotification();
        return rootView;
    }
    public void initRecyclerView () {
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView = rootView.findViewById(R.id.mojeKnjige0);
        recyclerView.setLayoutManager(gridLayoutManager);
        //upit za moje knjige
        try {
            userId = korisnikSP.getKorisnik().toString();
            if(userId != null || userId == "") {
                lista = klijent.sendM("mojeKnjige " + userId);
                adapter = new MojeKnjigeRVAdapter(getActivity(),lista);
                recyclerView.setAdapter(adapter);
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }


    }
    public void cancelNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();

        }
        catch (Exception e) {

        }
    }
    public void setListenerMojeKnjige(ListenerMojeKnjige listenerMojeKnjige) {
        this.listenerMojeKnjige = listenerMojeKnjige;
    }
    public void updateTab() {
        try {
            if(userId != null || userId != "") {
                userId = korisnikSP.getKorisnik().toString();
                lista = klijent.sendM("mojeKnjige " + userId);
                adapter = new MojeKnjigeRVAdapter(getActivity(),lista);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }


    }
}
