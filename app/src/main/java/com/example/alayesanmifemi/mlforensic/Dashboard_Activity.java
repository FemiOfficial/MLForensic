package com.example.alayesanmifemi.mlforensic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Alayesanmi Femi on 05/05/2018.
 */

public class Dashboard_Activity extends AppCompatActivity implements View.OnClickListener {

    CardView btn_case_history;
    CardView btn_view_profile;
    CardView btn_create_case;
    TextView login_username;
    TextView login_email;
    MyDBHandler dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        btn_case_history = (CardView) findViewById(R.id.case_history);
        btn_view_profile = (CardView)findViewById(R.id.profile_view);
        btn_create_case = (CardView)findViewById(R.id.create_case);
        btn_create_case.setOnClickListener(this);
        login_username = (TextView)findViewById(R.id.login_username);
        btn_case_history.setOnClickListener(this);
        btn_view_profile.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        String[] profileDetails = getUser();
        login_username.setText(profileDetails[0] + " " + profileDetails[1]);
        //login_email.setText(profileDetails[2]);

        setSupportActionBar(toolbar);
    }

    public String[] getUser(){
        dbHandler = new MyDBHandler(this, null, null, 1);
        String[] profileDetails =  dbHandler.userProfile(getUserEmail());
        return profileDetails;
    }
    public String getUserEmail(){
        Intent intent = getIntent();
        String username = intent.getExtras().getString("EMAIL");
        return username;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.case_history:
                Intent intent = new Intent();
                intent.setClass(this, CaseHistory_Activity.class);
                startActivity(intent);
                break;
            case R.id.profile_view:
                Intent intent1 = new Intent(this, Profile_Activity.class);
                intent1.putExtra("email", login_username.getText().toString());
                startActivity(intent1);
                break;
            case R.id.create_case:
                Intent intent2  = new Intent(this, DocumentCaseActivity.class);
                String[] profileDetails = getUser();
                intent2.putExtra("firstname", profileDetails[0]);
                intent2.putExtra("lastname", profileDetails[1]);
                intent2.putExtra("email", profileDetails[2]);
                intent2.putExtra("id", profileDetails[3]);
                startActivity(intent2);
        }
    }
}
