package com.dcodestar.googleplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncDownload.AsyncDownloadComplete{

    private static final String TAG = "MainActivity";
    RecyclerView pharmacyListRecyclerView;
    ArrayList<Pharmacy> pharmacyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Location location=null;
        LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PermissionChecker.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if(location==null){
            location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        Log.d(TAG, "onCreate: latitude is "+latitude+" and longitude is "+longitude);
        String nearbyPlacesUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=20000&type=pharmacy&key="+ConstantData.PLACES_API_KEY;
        Log.d(TAG, "onCreate: nearbyPlacesUrl is "+nearbyPlacesUrl);
        AsyncDownload asyncDownload=new AsyncDownload(this);
        asyncDownload.execute(nearbyPlacesUrl);

        pharmacyListRecyclerView=findViewById(R.id.pharmacyListRecyclerView);
        
    }

    @Override
    public void onDownloadComplete(String s) {
        objectifyJson(s);
    }

    void objectifyJson(String s){

    }
}
