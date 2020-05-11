package com.example.knjizara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailKorpaRVAdapter extends RecyclerView.Adapter<DetailKorpaRVAdapter.ViewHolder> {

    ArrayList<Knjiga> niz0 = new ArrayList<>();
    private static final String TAG = "DetailKorpaRVAdapter";
    public Context context;
    public KorpaActivity korpaActivity;

    public DetailKorpaRVAdapter(Context context, ArrayList<Knjiga> niz0) {
        this.context = context;
        korpaActivity = (KorpaActivity) ((Activity) context);
        this.niz0 = niz0;

    }

    @Override
    public void onBindViewHolder(@NonNull final DetailKorpaRVAdapter.ViewHolder holder, final int position) {

        String naslov = niz0.get(position).getNaslov();

        String cena = niz0.get(position).getCena();
        if(!cena.startsWith("Bespl")) {
            cena+=" RSD";
        }
        holder.naslov.setText(naslov);
        holder.cena.setText(cena);
        holder.layoutKorpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"isbn");
            }
        });
        holder.brisanjeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Knjiga knjiga = niz0.get(position);
                korpaActivity.obrisiIzKorpe(knjiga);
                if(v.equals(holder.brisanjeBtn)) {
                    niz0.remove(position);
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(),niz0.size());
                    if (niz0.size() == 1) {
                        notifyDataSetChanged();
                    }
                    korpaActivity.azurirajStanjeKorpe();

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return niz0.size();
    }

    @NonNull
    @Override
    public DetailKorpaRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knjiga_korpa_detail,parent,false);
        return new DetailKorpaRVAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutKorpa;
        ImageView slika;
        TextView naslov;
        TextView cena;
        Button brisanjeBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            layoutKorpa = itemView.findViewById(R.id.layoutKorpa);
            slika = itemView.findViewById(R.id.slikaKorpa);
            naslov = itemView.findViewById(R.id.naslovKorpa);
            cena = itemView.findViewById(R.id.cenaKorpa);
            brisanjeBtn = itemView.findViewById(R.id.brisanjeBtn);

        }
    }
}
