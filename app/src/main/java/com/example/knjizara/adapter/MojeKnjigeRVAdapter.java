package com.example.knjizara.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.activity.KnjigaDetail;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class MojeKnjigeRVAdapter extends RecyclerView.Adapter<MojeKnjigeRVAdapter.ViewHolder> {
    JSONArray niz0;
    private static final String TAG = "MojeKnjigeRVAdapter";
    public Context context;


    public MojeKnjigeRVAdapter(Context context, JSONArray niz0) {
        this.context = context;
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
        try {
            String naslov = niz0.getJSONArray(position).get(3).toString();

            holder.naslov.setText(naslov);
            holder.autor.setText(niz0.getJSONArray(position).get(4).toString());
            holder.cena.setText("");

            holder.slika2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String knjiga = niz0.getJSONArray(position).get(1).toString();
                        Toast.makeText(v.getContext(),""+knjiga,Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e) {

                    }

                }
            });



            String fajl = niz0.getJSONArray(position).get(1).toString()+".jpg";
            holder.setSlika(fajl);


        }
        catch (Exception e) {

        }




    }




    @Override
    public int getItemCount() {
        return niz0.length();
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
        public void setSlika(String fajl) {
            File fileName = new File(context.getFilesDir().getAbsolutePath()+"/slike_knjiga/"+fajl);
            if(!fileName.exists()) {
                File fileName2 = new File(context.getFilesDir().getAbsolutePath()+"/slike_knjiga/"+"noBook.png");
                Picasso.get().load(fileName2).into(slika);
            }
            else {
                Picasso.get().load(fileName).into(slika);
            }

        }
    }
}
