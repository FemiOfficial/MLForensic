package com.example.alayesanmifemi.mlforensic;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//public class DocumentCaseActivity extends AppCompatActivity implements
//        CreateCase_1.OnFragmentInteractionListener, CreateCase_2.OnFragmentInteractionListener  {
public class DocumentCaseActivity extends AppCompatActivity implements View.OnClickListener{

    MyDBHandler dbHandler;

    EditText detective_user;
    EditText detective_location;
    EditText case_title;
    EditText weather_condition;
    EditText category;
    EditText scene_descrip;
    DatePicker incident_date;
    TimePicker arrival_time;
    TimePicker departure_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_case);

        detective_user = findViewById(R.id.detective_name);
        detective_location = findViewById(R.id.incident_location);
        case_title = findViewById((R.id.title_case));
        weather_condition = findViewById(R.id.weather_condition);
        category = findViewById(R.id.category);
        scene_descrip = findViewById(R.id.scene_description);
        incident_date = findViewById(R.id.incident_date);

        arrival_time = findViewById(R.id.time_arrival);

        departure_time = findViewById(R.id.time_departure);


        String detectiveDetails[] = getUser();

        detective_user.setText(detectiveDetails[0] + " " + detectiveDetails[1]);

        Button save_btn = findViewById(R.id.btn_save_documentation);
        save_btn.setOnClickListener(this);

        //Collecting logged credentials
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_save_documentation:
                    createCase();
                    break;
            }
    }
    public void createCase(){
        int day = incident_date.getDayOfMonth();
        int month = incident_date.getMonth();
        int year = incident_date.getYear();
        String date_format = String.format("%d %s %d %s %d", day, "-", month, "-", year);

        int arriv_hour = arrival_time.getCurrentHour();
        int arriv_min = arrival_time.getCurrentMinute();
        String arriv_time_format = String.format("%d %s %d", arriv_hour, " : ", arriv_min);

        int depart_hour = departure_time.getCurrentHour();
        int depart_min = departure_time.getCurrentMinute();
        String depart_time_format = String.format("%d %s %d",depart_hour, " : ", depart_min);

        String detectiveDetails[] = getUser();

        String detective_id = detectiveDetails[3];

        Cases cases = new Cases(detective_id, case_title.getText().toString() ,
                detective_user.getText().toString() ,date_format,
                detective_location.getText().toString(), category.getText().toString(),
                scene_descrip.getText().toString(),arriv_time_format,
                depart_time_format, weather_condition.getText().toString());

        String case_object = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s", cases.getDetective_id(), cases.getTitle()
        , cases.getDetective(), cases.getIncident_date(), cases.getCrimeType(), cases.getDescription(), cases.getArrivalTimeDate(),
                cases.getDepartureTimeDate(), cases.getWeather());
        //dbHandler.createCase(cases);
        Toast.makeText(DocumentCaseActivity.this, case_object, Toast.LENGTH_LONG).show();

    }
    public String[] getUser(){
        Intent intent = getIntent();
        String[] user = new String[4];
        user[0] = intent.getExtras().getString("firstname");
        user[1] = intent.getExtras().getString("lastname");
        user[2] = intent.getExtras().getString("email");
        user[3] = intent.getExtras().getString("id");
//        String[] userProfile = dbHandler.userProfile(username);
//        String userEmail = userProfile[2];
        return user;
    }

//      detective_name.setText(profileDetails[0] + " "+ profileDetails[1]);

//        TabLayout tablayout = (TabLayout)findViewById(R.id.tablayout);
//      tablayout.addTab(tablayout.newTab().setText("Arrival/Departutre"));
//        tablayout.addTab(tablayout.newTab().setText("Scene Details"));
//        tablayout.addTab(tablayout.newTab().setText("Evidence Analysis"));
//        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
//
//        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

//    public void setTextFragment(){
//        Fragment fragment_1 = getFragmentManager().findFragmentById(R.layout.fragment_create_case_1);
//        EditText detective_user = (EditText) fragment_1.getView().findViewById(R.id.detective_name);
//        detective_user.setText(getUser());
//    }
}
