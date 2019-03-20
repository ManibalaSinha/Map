package com.example.manibala.map;

import android.app.Dialog;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity {

     GoogleMap mMap;
     private static final int ERROR_DIALOG_REQUEST=9002;
     private static final double
        SEATTLE_LAT=47.60621,
        SEATTLE_LNG=-122.33207,
        SYDNEY_LAT=-33.867487,
        NEWYORK_LAT=40.714353,
        NEWYORK_LNG=-74.005973;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(servicesOK()){
            if(initMap()) {
                Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
                gotoLocation(SEATTLE_LAT, SEATTLE_LNG, 25);
            } else {
               // Toast.makeText(this, "map not connected!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean servicesOK(){
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(isAvailable==ConnectionResult.SUCCESS){
            return true;
        } else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,this,ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this,"Cant connect to mapping service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private boolean initMap(){
        if(mMap == null){
           SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

               //mMap = mapFragment.getMap();
        }
        return (mMap!=null);
    }
    private void gotoLocation(double lat, double lng, float zoom){
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    //private void
    //public void
}
