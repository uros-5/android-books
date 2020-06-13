package com.example.knjizara.adapter;

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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.activity.KnjigaDetail;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class TopLevelRVAdapter extends RecyclerView.Adapter<TopLevelRVAdapter.ViewHolder> {
    ArrayList<ArrayList> niz0 = new ArrayList<>();
    private static final String TAG = "TopLevelRVAdapter";
    public int duzina = 6;
    public Context context;

//    public TopLevelRVAdapter(Context context, ArrayList<Knjiga> niz0) {
//        this.context = context;
//        this.niz0 = niz0;
//
//    }

    public TopLevelRVAdapter(Context context, ArrayList<ArrayList> niz0) {
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

        String naslov = niz0.get(position).get(1).toString();

        String cena = niz0.get(position).get(2).toString();
        if(!cena.startsWith("0")) {
            cena+=" RSD";
        }
        else if(cena.startsWith("0")) {
            cena = "Besplatno";
        }
        holder.naslov.setText(naslov);
        holder.autor.setText(niz0.get(position).get(3).toString());
        holder.cena.setText(cena);
        holder.slika2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String knjiga = niz0.get(position).get(0).toString();;
                String id = niz0.get(position).get(5).toString();
                Intent intent = new Intent(v.getContext(),
                        KnjigaDetail.class);

                intent.putExtra(KnjigaDetail.EXTRA_ID, id);
                v.getContext().startActivity(intent);
                Animatoo.animateInAndOut(context);

            }
        });



        String fajl = niz0.get(position).get(0).toString()+".jpg";

        holder.setSlika(fajl);


    }


    @Override
    public int getItemCount() {
        return duzina;
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
            Picasso.get().load(fileName).into(slika);
        }
    }
    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }
}
