package com.example.alayesanmifemi.mlforensic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    CardView btn_app_info;
    CardView btn_create_case;
    CardView btn_evidence_analysis;
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
        btn_app_info = (CardView)findViewById(R.id.app_info);
        btn_evidence_analysis = (CardView)findViewById(R.id.evidence_analysis);
        btn_evidence_analysis.setOnClickListener(this);
        btn_app_info.setOnClickListener(this);
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
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_Activity.this);

        builder.setTitle("Log Out")
                .setMessage("Do you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dashboard_Activity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
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
                String[] profileDetail = getUser();
                intent.putExtra("firstname", profileDetail[0]);
                intent.putExtra("lastname", profileDetail[1]);
                intent.putExtra("email", profileDetail[2]);
                intent.putExtra("id", profileDetail[3]);
                startActivity(intent);
                break;
            case R.id.profile_view:
                Intent intent1 = new Intent(this, Profile_Activity.class);
                String[] profileDetail1 = getUser();
                intent1.putExtra("email", profileDetail1[2]);
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
                break;
            case R.id.evidence_analysis:
                Intent intent3 = new Intent(this, CameraActivity.class);
                startActivity(intent3);
                break;
            case R.id.app_info:
                Intent intent4 = new Intent(this, AppInfo.class);
                startActivity(intent4);
                break;

        }
    }
}
