package com.example.alayesanmifemi.mlforensic;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CaseHistory_Activity extends AppCompatActivity {

    List<Cases> case_list;
    EditText detective_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        detective_name = (EditText)findViewById(R.id.detective_name_history);
        Intent intent = getIntent();
        String firstname = intent.getExtras().getString("firstname");
        String lastname =  intent.getExtras().getString("lastname");

        detective_name.setText("Detective: " + firstname +" "+ lastname);
        Intent intent2 = new Intent();
        intent2.setClass(this, CaseDetail_Activity.class);
        intent.putExtra("firstname", firstname);
        intent.putExtra("lastname", lastname);

        detective_name.setEnabled(false);

        case_list = new ArrayList<>();
        MyDBHandler dbHandler = new MyDBHandler(this, null,null, 1);

        int number_cases = dbHandler.countCases(getUserId());
        if(number_cases > 0){
            int i = 0;
            while(i<number_cases){
                case_list.add(dbHandler.displayCases(getUserId()).get(i));
                i++;
            }
        }else {
            Toast.makeText(CaseHistory_Activity.this, "No Case Documented Yet" , Toast.LENGTH_LONG).show();

        }

//        case_list.add(new Cases("1","Riot in Sambisa", "Reuben James",
//                "04-06-2018", "Ikeja", "Murder",
//                "People died","04-06-2018, 12:30PM",
//                "04-06-2018, 02:30PM", "Sunny", R.drawable.murder ));

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
    public String getUserId(){
        Intent intent = getIntent();
        String id =  intent.getExtras().getString("id");
        return id;
        }

    }



