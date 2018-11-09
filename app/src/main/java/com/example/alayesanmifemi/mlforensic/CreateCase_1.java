package com.example.alayesanmifemi.mlforensic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateCase_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateCase_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateCase_1 extends Fragment {

    MyDBHandler dbHandler;
    Cases cases;


    private FragmentListener listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreateCase_1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CreateCase_1 newInstance(String param1, String param2) {
        CreateCase_1 fragment = new CreateCase_1();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public interface FragmentListener{
        void onInputSent(CharSequence input);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_create_case_1, container, false);

        EditText detective_user = v.findViewById(R.id.detective_name);
        EditText detective_location = v.findViewById(R.id.incident_location);
        EditText case_title = v.findViewById((R.id.title_case));
        EditText weather_condition = v.findViewById(R.id.weather_condition);
        EditText category = v.findViewById(R.id.category);
        EditText scene_descrip = v.findViewById(R.id.scene_description);
        DatePicker incident_date = v.findViewById(R.id.incident_date);
        int day = incident_date.getDayOfMonth();
        int month = incident_date.getMonth();
        int year = incident_date.getYear();
        String date_format = String.format("%d %s %d %s %d", day, "-", month, "-", year);

        TimePicker arrival_time = v.findViewById(R.id.time_arrival);
        int arriv_hour = arrival_time.getCurrentHour();
        int arriv_min = arrival_time.getCurrentMinute();
        String arriv_time_format = String.format("%d %s %d", arriv_hour, " : ", arriv_min);

        TimePicker departure_time = v.findViewById(R.id.time_departure);
        int depart_hour = departure_time.getCurrentHour();
        int depart_min = departure_time.getCurrentMinute();
        String depart_time_format = String.format("%d %s %d",depart_hour, " : ", depart_min);

      final String detectiveDetails[] = getUserDetails();
        detective_user.setText(detectiveDetails[0] + " " + detectiveDetails[1]);

        String detective_id = detectiveDetails[3];

        cases = new Cases(detective_id, case_title.getText().toString() ,date_format,
                detective_location.getText().toString(), category.getText().toString(),
                scene_descrip.getText().toString(),arriv_time_format,
                depart_time_format, weather_condition.getText().toString());

        Button save_btn = v.findViewById(R.id.btn_save_documentation);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dbHandler.createCase(cases);
                    Snackbar.make(view, "case created", Snackbar.LENGTH_LONG).show();
                }catch(Exception e) {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }

            }
        });
        // Inflate the layout for this fragment
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public String[] getUserDetails(){
        DocumentCaseActivity activity = (DocumentCaseActivity) getActivity();
        String[] userEmail = activity.getUser();
        return  userEmail;
    }


}
