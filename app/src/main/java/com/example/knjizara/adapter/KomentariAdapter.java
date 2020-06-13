package com.example.knjizara.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.activity.KnjigaDetail;
import com.example.knjizara.R;

import java.io.File;
import java.util.ArrayList;

public class KomentariAdapter extends RecyclerView.Adapter<KomentariAdapter.ViewHolder> {
    ArrayList<ArrayList> niz0 = new ArrayList<>();
    private static final String TAG = "KomentariAdapter";
    public int duzina = 6;
    public Context context;

//    public TopLevelRVAdapter(Context context, ArrayList<Knjiga> niz0) {
//        this.context = context;
//        this.niz0 = niz0;
//
//    }

    public KomentariAdapter(Context context, ArrayList<ArrayList> niz0) {
        this.context = context;
        this.niz0 = niz0;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.komentari_za_knjigu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String osoba = niz0.get(position).get(1).toString();

        String komentar = niz0.get(position).get(0).toString();

        holder.osoba.setText(osoba);
        holder.komentar.setText(komentar);



    }


    @Override
    public int getItemCount() {
        return niz0.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView osoba;
        TextView komentar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            osoba = itemView.findViewById(R.id.osobaView);
            komentar = itemView.findViewById(R.id.komentarView);
        }
    }
    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }
}
