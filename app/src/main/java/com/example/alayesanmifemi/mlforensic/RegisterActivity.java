package com.example.alayesanmifemi.mlforensic;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Alayesanmi Femi on 05/05/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText password;
    EditText co_password;
    TextView test_printb;
    LinearLayout LinearLayout;

    MyDBHandler dbHandler;
    Button signUpBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        test_printb = (TextView)findViewById(R.id.test_printdb);
        firstname = (EditText) findViewById(R.id.reg_firstname);
        lastname = (EditText) findViewById(R.id.reg_lastname);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        co_password = (EditText) findViewById(R.id.reg_con_password);
        signUpBtn = (Button)findViewById(R.id.btn_register);
        dbHandler = new MyDBHandler(this, null,null, 1);
        signUpBtn.setOnClickListener(this);
       // printDB();
    }
    //Register User to SQLite Database

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                signUp();
                Snackbar.make( view, "Registration Successful", Snackbar.LENGTH_LONG).show();
        }
    }
    public String printDB(){
        String dbString = dbHandler.userDatabase();
        //test_printb.setText(dbString);
        return dbString;
    }

    public void signUp(){
        Users users = new Users(firstname.getText().toString(), lastname.getText().toString(),
                email.getText().toString(), password.getText().toString());
        dbHandler.createUser(users);
          // Toast.makeText(RegisterActivity.this, "Registration Successfull" , Toast.LENGTH_LONG).show();


           //        test_printb.setText(printDB());
     //   System.out.println(printDB());
    }



}
