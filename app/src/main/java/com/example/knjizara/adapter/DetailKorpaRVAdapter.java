package com.example.knjizara.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.fragments.Tab3;
import com.example.knjizara.model.Knjiga;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class DetailKorpaRVAdapter extends RecyclerView.Adapter<DetailKorpaRVAdapter.ViewHolder> {

    ArrayList<String> niz0 = new ArrayList<>();
    private static final String TAG = "DetailKorpaRVAdapter";
    public Context context;
    public Tab3 korpaActivity;
    Klijent klijent;
    ArrayList<ArrayList> knjiga;

    public DetailKorpaRVAdapter(Fragment context, ArrayList<String> niz0) {
        this.context = context.getContext();
        korpaActivity = (Tab3) ((Fragment) context);
        this.niz0 = niz0;

    }

    @Override
    public void onBindViewHolder(@NonNull final DetailKorpaRVAdapter.ViewHolder holder, final int position) {

        klijent = new Klijent();

        knjiga = klijent.sendM("id "+ niz0.get(position).toString());

        String naslov = knjiga.get(0).get(1).toString();

        String cena = knjiga.get(0).get(2).toString();
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
                String knjiga = niz0.get(position);
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
        String fajl = knjiga.get(0).get(0).toString()+".jpg";
        holder.setSlika(fajl);


    }

    public void azurirajMojeKnjige() {

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

        public void setSlika(String fajl) {
            File fileName = new File(context.getFilesDir().getAbsolutePath()+"/slike_knjiga/"+fajl);
            Picasso.get().load(fileName).into(slika);
        }
    }
}
