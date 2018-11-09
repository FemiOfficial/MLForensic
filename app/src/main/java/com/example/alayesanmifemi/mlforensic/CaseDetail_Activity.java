package com.example.alayesanmifemi.mlforensic;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CaseDetail_Activity extends AppCompatActivity {

    private TextView case_title, case_location, case_category, case_category1,
                    case_description, case_arrivalTime,
            case_departureTime, case_weather;
    //private ImageView thumbnail_image;
    //private LinearLayout layout_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        case_category = (TextView) findViewById(R.id.case_category);
        case_category1 = (TextView) findViewById(R.id.case_category1);
        case_title = (TextView) findViewById(R.id.case_title);
        case_weather = (TextView) findViewById(R.id.case_weather);
        case_location = (TextView) findViewById(R.id.case_location);
//        case_detective = (TextView) findViewById(R.id.case_detective);
        case_description = (TextView) findViewById(R.id.case_description);
        case_arrivalTime = (TextView) findViewById(R.id.case_arrivalTime);
        case_departureTime = (TextView) findViewById(R.id.case_departureTime);
        //layout_image = (LinearLayout) findViewById(R.id.case_thumbnail);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Location = intent.getExtras().getString("Location");
        String Category = intent.getExtras().getString("Category");
        // int Thumbnail = intent.getExtras().getInt("Thumbnail");
        String Description = intent.getExtras().getString("Description");
        String ArrivalTime = intent.getExtras().getString("Arrival Time");
        String DepartureTime = intent.getExtras().getString("Departure Time");
        String Weather = intent.getExtras().getString("Weather");

//        Intent intent2 = getIntent();
//        String firstname = intent2.getExtras().getString("firstname");
//        String lastname = intent2.getExtras().getString("lastname");


        case_title.setText(Title);
        case_departureTime.setText(DepartureTime);
        case_arrivalTime.setText(ArrivalTime);
        case_description.setText(Description);
        case_location.setText(Location);
        case_category1.setText(Category);
        case_category.setText(Category);
        case_weather.setText(Weather);
//        case_detective.setText(firstname + " " + lastname);
//      //  layout_image.setBackgroundResource(Thumbnail);

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
