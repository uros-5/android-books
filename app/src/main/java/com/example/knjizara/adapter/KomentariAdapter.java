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

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class KomentariAdapter extends RecyclerView.Adapter<KomentariAdapter.ViewHolder> {
    JSONArray niz0;
    private static final String TAG = "KomentariAdapter";
    public int duzina = 6;
    public Context context;

//    public TopLevelRVAdapter(Context context, ArrayList<Knjiga> niz0) {
//        this.context = context;
//        this.niz0 = niz0;
//
//    }

    public KomentariAdapter(Context context, JSONArray niz0) {
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

        try {
            String osoba = niz0.getJSONArray(position).get(2).toString();

            String komentar = niz0.getJSONArray(position).get(0).toString();

            holder.osoba.setText(osoba);
            holder.komentar.setText(komentar);
        }
        catch (Exception e) {

        }




    }


    @Override
    public int getItemCount() {
        return niz0.length();
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
