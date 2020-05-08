package com.example.knjizara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ToolbarActivityListener {
    public int glavniLayout;
    public Context context;
    public LinearLayout layout;
    public ToolbarActivityListener (int resId,Context context) {
        this.glavniLayout = resId;
        this.context = context;
        setSelectedLayout();

    }


    public void setSelectedLayout() {
        layout = (LinearLayout) ((AppCompatActivity) context).findViewById(glavniLayout);
        layout.setBackgroundColor(context.getResources().getColor(R.color.selectedItem));
    }

    public void dodajListener(final int resId) {
        LinearLayout layout = (LinearLayout) ((AppCompatActivity) context).findViewById(resId);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resId != glavniLayout) {
                    startActivity(resId);
                }


            }
        });
    }
    public void startActivity(int resId) {
        Intent intent;
        if (glavniLayout==R.id.homeLayoutTopLevel) {
            switch(resId){
                case R.id.mojeKnjigeLayoutTopLevel:
                    intent = new Intent(context,MojeKnjigeActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.kolicaLayoutTopLevel:
                    intent = new Intent(context,KorpaActivity.class) ;
                    context.startActivity(intent);
                    break;
            }
        }
        else if (glavniLayout == R.id.mojeKnjigeLayoutTopLevel) {
            switch(resId){
                case R.id.homeLayoutTopLevel:
                    intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.kolicaLayoutTopLevel:
                    intent = new Intent(context,KorpaActivity.class) ;
                    context.startActivity(intent);
                    break;
            }
        }
        else if (glavniLayout == R.id.kolicaLayoutTopLevel) {
            switch(resId){
                case R.id.homeLayoutTopLevel:
                    intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.mojeKnjigeLayoutTopLevel:
                    intent = new Intent(context,MojeKnjigeActivity.class) ;
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
