package com.example.knjizara.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;

import java.util.ArrayList;

public class KnjigeAdapter extends ArrayAdapter<Knjiga> {
    private static final String TAG = "KnjigeAdapter";
    private Context mContext;
    private int mResource;
    private ArrayList<Knjiga> mObjects;

    public KnjigeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Knjiga> objectList) {
        super(context, resource, objectList);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objectList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String naslov = getItem(position).getNaslov();
        String autor = getItem(position).getAutor();
        String cena = getItem(position).getCena();

        System.out.println("NASLOV::"+naslov);

        Knjiga knjiga = new Knjiga(naslov,autor,cena);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvNaslov = (TextView) convertView.findViewById(R.id.naslov);
        TextView tvAutor = (TextView) convertView.findViewById(R.id.autor);
        TextView tvCena = (TextView) convertView.findViewById(R.id.cena);

        tvNaslov.setText(naslov);
        tvAutor.setText(autor);
        tvCena.setText(cena);

        return convertView;
    }
}
