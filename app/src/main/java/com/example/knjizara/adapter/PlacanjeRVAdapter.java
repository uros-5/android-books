package com.example.knjizara.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;

import java.util.ArrayList;

public class PlacanjeRVAdapter extends RecyclerView.Adapter<PlacanjeRVAdapter.ViewHolder> {
    ArrayList<String> niz0 = new ArrayList<>();
    ArrayList<ArrayList> knjiga;
    Klijent klijent;


    private static final String TAG = "PlacanjeRVAdapter";

    public PlacanjeRVAdapter(Context context, ArrayList<String> niz0) {
        this.niz0 = niz0;

    }

    @NonNull
    @Override
    public PlacanjeRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placanje_korpa_detail,parent,false);
        return new PlacanjeRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacanjeRVAdapter.ViewHolder holder, final int position) {


        klijent = new Klijent();

        knjiga = klijent.sendM("id "+niz0.get(position).toString());

        String naslov = knjiga.get(0).get(1).toString();

        String cena = knjiga.get(0).get(2).toString();

        holder.naslov.setText(naslov);
        holder.cena.setText(cena);



    }

    @Override
    public int getItemCount() {
        return niz0.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView naslov;
        TextView cena;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            naslov = itemView.findViewById(R.id.naslovPlacanje);
            cena = itemView.findViewById(R.id.cenaPlacanje);

        }
    }
}