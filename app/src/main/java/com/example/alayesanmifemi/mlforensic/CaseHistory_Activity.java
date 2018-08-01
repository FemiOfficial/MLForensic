package com.example.alayesanmifemi.mlforensic;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CaseHistory_Activity extends AppCompatActivity {

    List<Cases> case_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        case_list = new ArrayList<>();

        case_list.add(new Cases("1","Riot in Sambisa", "Reuben James",
                "04-06-2018", "Ikeja", "Murder",
                "People died","04-06-2018, 12:30PM",
                "04-06-2018, 02:30PM", "Sunny", R.drawable.murder ));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclyerview_id);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, case_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(recyclerAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
