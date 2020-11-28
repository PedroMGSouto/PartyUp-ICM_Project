package com.example.partyup.Features;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.partyup.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Frag_Map extends Fragment {
    private final int REQUEST_CODE_PERMISSION = 1002;
    private GPSTracker gpsTracker;
    private View rootView;
    private boolean grantedPermissions = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION);
        }
        else{
            grantedPermissions = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, null);

        //call this method to check gps enable or not
        if(grantedPermissions){
            setLocation();
        }


        return rootView;
    }

    public void setLocation() {
        gpsTracker = new GPSTracker(getContext());

        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            //position found, show in map
            setMap(latitude, longitude);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }
    }

    //this method is to show map
    public void setMap(final double latitude, final double longitude) {
        MapView mapView = (MapView) rootView.findViewById(R.id.map_view);
        mapView.onCreate(null);
        mapView.onResume();
        mapView.getMapAsync(
                new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googlemap) {
                        final GoogleMap map = googlemap;

                        MapsInitializer.initialize(getContext());
                        //change map type as your requirements
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        //user will see a blue dot in the map at his location
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        }
                        map.setMyLocationEnabled(true);
                        LatLng marker =new LatLng(latitude, longitude);

                        //move the camera default animation
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 8));

                        //add a default marker in the position
                        map.addMarker(new MarkerOptions().position(marker));

                    }
                }
        );
    }

}