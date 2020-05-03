package com.example.knjizara;

import android.content.Context;
import android.media.Image;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    ArrayList<Knjiga> niz1 = new ArrayList<>();
    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewAdapter(Context context, ArrayList<Knjiga> niz0, ArrayList<Knjiga> niz1) {
        this.niz0 = niz0;
        this.niz1 = niz1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.knjiga2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.naslov.setText(niz0.get(position).getNaslov());
        holder.autor.setText(niz0.get(position).getAutor());
        holder.cena.setText(niz0.get(position).getCena());

        holder.slika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"kLIKNuto! :d");
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
