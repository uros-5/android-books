package com.example.knjizara;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopLevelRVAdapter extends RecyclerView.Adapter<TopLevelRVAdapter.ViewHolder> {
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    private static final String TAG = "TopLevelRVAdapter";

    public TopLevelRVAdapter(Context context, ArrayList<Knjiga> niz0) {
        this.niz0 = niz0;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knjiga_top_level,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String naslov = niz0.get(position).getNaslov();

        String cena = niz0.get(position).getCena();
        if(!cena.startsWith("Bespl")) {
            cena+="RSD";
        }
        holder.naslov.setText(naslov);
        holder.autor.setText(niz0.get(position).getAutor());
        holder.cena.setText(cena);
        holder.slika2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Knjiga knjiga = niz0.get(position);
                Intent intent = new Intent(v.getContext(),
                        KnjigaDetail.class);

                intent.putExtra(KnjigaDetail.EXTRA_ID, knjiga);
                v.getContext().startActivity(intent);
                Log.d(TAG,"isbn");
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView slika2;
        ImageView slika;
        TextView naslov;
        TextView autor;
        TextView cena;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            slika2 = itemView.findViewById(R.id.slika2);
            slika = itemView.findViewById(R.id.slika);
            naslov = itemView.findViewById(R.id.naslov);
            autor = itemView.findViewById(R.id.autor);
            cena = itemView.findViewById(R.id.cena);

        }
    }
}
