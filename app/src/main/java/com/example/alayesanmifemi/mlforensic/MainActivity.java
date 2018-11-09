package com.example.alayesanmifemi.mlforensic;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login;
    TextView txt_user;
    TextView txt_pass;
//    TextView forgot_pass;
    TextView create_acct;
    RelativeLayout RelativeLayout;

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_user = (TextView) findViewById(R.id.txt_username);
        txt_pass = (TextView) findViewById(R.id.txt_password);
//        forgot_pass = (TextView) findViewById(R.id.forgot_pass);
        create_acct = (TextView) findViewById(R.id.create_acct);
        dbHandler = new MyDBHandler(this, null,
                null, 1);

        btn_login.setOnClickListener(this);
        create_acct.setOnClickListener(this);
      //  forgot_pass.setOnClickListener(this);

    }
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Exit App")
                .setMessage("Saying Goodbye already?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
//                Intent intent = new Intent();
//                intent.setClass(this, Dashboard_Activity.class);
//                startActivity(intent);

                if (verifyFromSQLite()){
                    Intent IntentAccounts =  new Intent(this, Dashboard_Activity.class);
                    IntentAccounts.putExtra("EMAIL", txt_user.getText().toString().trim());
                    txt_user.setText(null);
                    txt_pass.setText(null);
                    startActivity(IntentAccounts);
                }else{
                    //Toast.makeText(MainActivity.this, "Invalid Email or Password, please try again" , Toast.LENGTH_LONG).show();
                    Snackbar.make(view, "Invalid Email or Password, please try again", Snackbar.LENGTH_LONG).show();
                    txt_user.setText(null);
                    txt_pass.setText(null);
                }
                break;
            case R.id.create_acct:
                Intent intent2 = new Intent();
                intent2.setClass(this, RegisterActivity.class);
                startActivity(intent2);
                break;
//            case R.id.forgot_pass:
//                Toast.makeText(MainActivity.this, "Password will be sent to email", Toast.LENGTH_LONG).show();
//                break;

        }
    }
    private boolean verifyFromSQLite(){
        if(dbHandler.checkUser(txt_user.getText().toString().trim(),
                txt_pass.getText().toString().trim())){
            return true;

        }else{
            return false;


        }
    }

    }
