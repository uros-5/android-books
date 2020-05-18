package com.example.knjizara.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.NotificationHelper;
import com.example.knjizara.R;
import com.example.knjizara.activity.KorisnikInfoActivity;
import com.example.knjizara.activity.MainActivity2;
import com.example.knjizara.activity.PlacanjeActivity;
import com.example.knjizara.adapter.PlacanjeRVAdapter;
import com.example.knjizara.interfaces.FragmentStartListener;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.KorpaSP;
import com.example.knjizara.viewmodel.MojeKnjige;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

import java.util.ArrayList;


public class PlacanjeFragment extends Fragment {
    MojeKnjige mojeKnjige;
    RecyclerView recyclerView;
    public KorpaSP korpaSP;
    public KorisnikSP korisnikSP;
    public MojeKnjigeSP mojeKnjigeSP;
    private final String CHANNEL_ID = "PlacanjeActivity";
    NotificationHelper notificationHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        notificationHelper = new NotificationHelper(getActivity());


        korpaSP = new KorpaSP(getActivity());
        korpaSP.setStanje(null);
        korpaSP.setUkupnaCena();

        mojeKnjigeSP = new MojeKnjigeSP(getActivity());
        initRecyclerView();
        Button addBtn = (Button) getActivity().findViewById(R.id.addUMojeKnjigeBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("KLIKKKK");
            }
        });

        Button backBtn = (Button) getActivity().findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("KLIKKKK2222");
            }
        });
        return inflater.inflate(R.layout.fragment_placanje, container, false);
    }

    public void dodajUMojeKnjige1 (View view) {
        Intent intent = new Intent(getActivity(), MainActivity2.class);
        startActivity(intent);
        // toast


        final Toast toastProc = Toast.makeText(getActivity(),"Transakcija se obradjuje..",Toast.LENGTH_LONG);
        toastProc.show();
        Handler handlerProc = new Handler();
        handlerProc.postDelayed(new Runnable() {
            @Override
            public void run() {
                toastProc.cancel();
            }
        }, 2000);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Knjiga> niz0 = korpaSP.getKorpa();
                for (Knjiga knjiga:niz0) {
                    mojeKnjigeSP.dodaj(knjiga);
                }
                korpaSP.setAray(null);

                notificationHelper.createNotification("Uplata za knjige","Transakcija je uspesna.","MojeKnjigeActivity");

            }
        }, 5000);

    }

    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView = getActivity().findViewById(R.id.placanjeRV);

        recyclerView.setLayoutManager(layoutManager);
        PlacanjeRVAdapter adapter = new PlacanjeRVAdapter(getActivity(),korpaSP.getKorpa());
        recyclerView.setAdapter(adapter);

    }

    public void nazad(View view) {
        Intent intent = new Intent(getActivity(), KorisnikInfoActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(getActivity());
    }

}
