package xyz.himanshu.womensafety.Fragments;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import xyz.himanshu.womensafety.MyLocationListener;
import xyz.himanshu.womensafety.R;
import xyz.himanshu.womensafety.db;

import static xyz.himanshu.womensafety.R.id.container;


public class Home extends Fragment {
    ImageView Send;
    db dbhandler;

    public static Fragment newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbhandler.number() == 2) {
                    String phoneNo1 = dbhandler.databaseToPhoneFirst();
                    String phoneNo2 = dbhandler.databaseToPhoneSecond();
                    Double latitude = 0.0, longitude;
                    String message = "Need Your Help. I am in danger.";
                    LocationManager mlocManager = null;
                    LocationListener mlocListener;
                    mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    mlocListener = new MyLocationListener();
                    mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
                    if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        latitude = MyLocationListener.latitude;
                        longitude = MyLocationListener.longitude;
                        message = message + "\n My Location is: Latitude =" + latitude + " and longitude =" + longitude;
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        if (latitude == 0.0) {
                            Toast.makeText(getActivity(), "Currently gps has not found your location....", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "GPS is currently off...", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });




    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_home, container, false);


    }
}