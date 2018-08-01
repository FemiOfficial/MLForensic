package com.example.alayesanmifemi.mlforensic;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DocumentCaseActivity extends AppCompatActivity implements
        CreateCase_1.OnFragmentInteractionListener, CreateCase_2.OnFragmentInteractionListener  {

    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_case);

        //Collecting logged credentials


//      detective_name.setText(profileDetails[0] + " "+ profileDetails[1]);

        TabLayout tablayout = (TabLayout)findViewById(R.id.tablayout);
//      tablayout.addTab(tablayout.newTab().setText("Arrival/Departutre"));
        tablayout.addTab(tablayout.newTab().setText("Scene Details"));
        tablayout.addTab(tablayout.newTab().setText("Evidence Analysis"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

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
//    public void setTextFragment(){
//        Fragment fragment_1 = getFragmentManager().findFragmentById(R.layout.fragment_create_case_1);
//        EditText detective_user = (EditText) fragment_1.getView().findViewById(R.id.detective_name);
//        detective_user.setText(getUser());
//    }
}
