package com.example.knjizara.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.R;
import com.example.knjizara.adapter.TopLevelRVAdapter;
import com.example.knjizara.model.Knjiga;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class KnjizaraInfo {
    ObjectInputStream in;
    Context context;
    public KnjizaraInfo (Context context) {
        this.context = context;
    }
    public void otvoriZaCitanje() {

        try {
            InputStream is = context.getAssets().open("knjizaraInfo.out");
//            InputStream is = getApplicationContext().getAssets().open("knjizaraInfo.out");
            this.in = new ObjectInputStream(
                            is);
        } catch (Exception e) {
            System.out.println("Greska1:" + e.getMessage());
        }

    }
    public ArrayList<Knjiga> getNiz (int br){
        otvoriZaCitanje();
        try {
            if(br == 0) {
                ArrayList<Knjiga> niz = (ArrayList<Knjiga>) (this.in.readObject());
                zatvoriZaCitanje();
                return niz;
            }
            else {
                this.in.readObject();
                ArrayList<Knjiga> niz = (ArrayList<Knjiga>) (this.in.readObject());
                zatvoriZaCitanje();
                return niz;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Knjiga> mixBooks () {
        ArrayList<Knjiga> niz0 = getNiz(0);
        ArrayList<Knjiga> niz1 = getNiz(1);

        ArrayList<Knjiga> niz2 = new ArrayList<Knjiga>();
        while(niz2.size() <50 ) {

            int nizBr = randBroj(2);
            int nizId = 0;
            if(nizBr == 0) {

                nizId = randBroj(niz0.size());
                Knjiga knjiga = niz0.get(nizId);
                if(!niz2.contains(knjiga)) {
                    niz2.add(knjiga);
                }
            }
            else if (nizBr == 1) {
                nizId = randBroj(niz1.size());
                Knjiga knjiga = niz1.get(nizId);
                if(!niz2.contains(knjiga)) {
                    niz2.add(knjiga);
                }
            }

        }
        return niz2;
    }
    public ArrayList<Knjiga> getAllBooks () {
        ArrayList<Knjiga> niz0 = getNiz(0);
        ArrayList<Knjiga> niz1 = getNiz(1);
        ArrayList<Knjiga> niz2 = new ArrayList<Knjiga>();

        niz2.addAll(niz0);
        niz2.addAll(niz1);

        return niz2;
    }
    public ArrayList<Knjiga> getBooksCategory (String kategorija) {
        ArrayList<Knjiga> niz = getAllBooks();
        ArrayList<Knjiga> nizKat = new ArrayList<>();
        for(Knjiga knjiga:niz) {
            if(knjiga.getKategorija().equals(kategorija)) {
                nizKat.add(knjiga);
            }
        }
        return nizKat;
    }
    public void initKategorijaRV(ArrayList<Knjiga> niz) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);

        RecyclerView recyclerView = ((AppCompatActivity) context).findViewById(R.id.kategorijaLista0);
        recyclerView.setLayoutManager(gridLayoutManager);
        TopLevelRVAdapter adapter = new TopLevelRVAdapter(context,niz);
        adapter.setDuzina(niz.size());
        recyclerView.setAdapter(adapter);
    }

    public int randBroj(int br) {
        Random random = new Random();
        return random.nextInt(br);
    }


    public void zatvoriZaCitanje () {
        try {
            this.in.close();
        } catch (Exception e2) {
            Log.i("Greska:", e2.getMessage());
        }
    }


}
