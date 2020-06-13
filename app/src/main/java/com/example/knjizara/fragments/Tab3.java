package com.example.knjizara.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.R;
import com.example.knjizara.activity.PlacanjeActivity;
import com.example.knjizara.adapter.DetailKorpaRVAdapter;
import com.example.knjizara.adapter.PagerAdapter;
import com.example.knjizara.interfaces.FragmentStartListener;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.KorpaSP;
import com.example.knjizara.viewmodel.ListenerKorpa;


public class Tab3 extends Fragment {
    View rootView;
    RecyclerView recyclerView;
    public KorpaSP korpaSP;
    public KorisnikSP korisnikSP;
    public FragmentStartListener fragmentStartListener;
    AppCompatActivity activity;
    public DetailKorpaRVAdapter adapter;
    public ListenerKorpa listenerKorpa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab3, container, false);

        korpaSP = new KorpaSP(getActivity());
//        korpaSP.sendTab3(this);
        initRecyclerView();
        korpaSP.setStanje(rootView);
        korisnikSP = new KorisnikSP(getActivity());
        setListenerZavrsi();
        return rootView;
    }
    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView = rootView.findViewById(R.id.knjige_iz_korpe0);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new DetailKorpaRVAdapter(this,korpaSP.getKorpa());
        recyclerView.setAdapter(adapter);

    }
    public void obrisiIzKorpe (String knjiga) {
        korpaSP.obrisi(knjiga);
        Parcelable recyclerViewState;
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
    }
    public void azurirajStanjeKorpe () {
        korpaSP.setStanje(rootView);
    }
    public void setListenerZavrsi() {
        Button zkBtn = rootView.findViewById(R.id.zkBtn);
        zkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zavrsiKupovinu();
            }
        });

    }
    public void zavrsiKupovinu() {

        if(korpaSP.getSizeOfKorpa()>0) {
            PlacanjeActivity placanjeActivity = new PlacanjeActivity();
            Intent intent = new Intent(getActivity(), placanjeActivity.getClass());
            startActivity(intent);
            Animatoo.animateSlideLeft(getActivity());
        }
        else {
            Toast.makeText(getActivity(),"Niste dodali knjige u korpu.",Toast.LENGTH_LONG).show();
        }

    }



    public void updateTab() {
        azurirajStanjeKorpe();
        adapter = new DetailKorpaRVAdapter(this,korpaSP.getKorpa());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }
}
