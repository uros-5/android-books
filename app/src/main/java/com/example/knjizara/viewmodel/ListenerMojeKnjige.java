package com.example.knjizara.viewmodel;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.knjizara.adapter.MojeKnjigeRVAdapter;

public class ListenerMojeKnjige {
    public int counter = 0;
    public MojeKnjigeSP mojeKnjigeSP;


    public ListenerMojeKnjige (Context context) {
        mojeKnjigeSP = new MojeKnjigeSP(context);
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
    public int getCounter() {
        return counter;
    }
    public int getSizeOfSP() {
        return mojeKnjigeSP.getMojeKnjige().size();
    }
    public boolean compare () {
        if(getCounter()==getSizeOfSP()) {
            return true;
        }
        else {
            return false;
        }
    }



}
