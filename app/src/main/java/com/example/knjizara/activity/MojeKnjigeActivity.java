package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.NotificationHelper;
import com.example.knjizara.R;
import com.example.knjizara.ToolbarActivityListener;
import com.example.knjizara.adapter.MojeKnjigeRVAdapter;
import com.example.knjizara.viewmodel.MojeKnjige;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

public class MojeKnjigeActivity extends AppCompatActivity {

    MojeKnjige mojeKnjige;
    MojeKnjigeSP mojeKnjigeSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moje_knjige);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.mojeKnjigeLayoutTopLevel,MojeKnjigeActivity.this);

        toolbarActivityListener.dodajListener(R.id.homeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.kolicaLayoutTopLevel);

        mojeKnjigeSP = new MojeKnjigeSP(this);
        initRecyclerView();
        cancelNotification();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LinearLayout layout = (LinearLayout) findViewById(R.id.mojeKnjigeLayoutTopLevel);
        layout.setBackgroundResource(0);
    }

    public void initRecyclerView () {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.mojeKnjige0);
        recyclerView.setLayoutManager(gridLayoutManager);
        MojeKnjigeRVAdapter adapter = new MojeKnjigeRVAdapter(this,mojeKnjigeSP.getMojeKnjige());
        recyclerView.setAdapter(adapter);
    }

    public void cancelNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();

        }
        catch (Exception e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch(item.getItemId()) {
//            case R.id.idi_nazad:
//
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
//                return true;
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }

}
