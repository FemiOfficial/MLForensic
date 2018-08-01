package com.example.alayesanmifemi.mlforensic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Profile_Activity extends AppCompatActivity {


    EditText firstname;
    EditText lastname;
    EditText email;
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        firstname = (EditText)findViewById(R.id.profile_first_name);
        lastname = (EditText)findViewById(R.id.profile_last_name);
        email = (EditText)findViewById(R.id.profile_email);

        Intent intent = getIntent();
        String user_email = intent.getExtras().getString("email");
        dbHandler = new MyDBHandler(this, null, null, 1);

      String[] profileDetails = dbHandler.userProfile(user_email);

//      if(profileDetails[0] == null){
//            Toast.makeText(Profile_Activity.this,  "Nothing", Toast.LENGTH_LONG).show();
//        }else{
//          Toast.makeText(Profile_Activity.this,  profileDetails[0], Toast.LENGTH_LONG).show();
//        }

        firstname.setText(profileDetails[0]);
        lastname.setText(profileDetails[1]);
        email.setText(profileDetails[2]);

    }

}
