package com.example.empmgtsystem.FriendList;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.location.Geocoder;
import com.example.empmgtsystem.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String addrress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        addrress = intent.getStringExtra("myaddress");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34.05648, 151.8753);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Straithfield"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if(addrress.equals("")) {
            LatLng position = new LatLng(-33.6638,151.2093);
            mMap.addMarker(new MarkerOptions().position(position).title("Straithfield"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        } else {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocationName(addrress, 5);
                double latitude = addresses.get(0).getLatitude();
                double longtitude = addresses.get(0).getLongitude();
                LatLng position = new LatLng(latitude,longtitude);
                mMap.addMarker(new MarkerOptions().position(position).title("Market in"+addresses));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longtitude), 12.0f));
            }
            catch (Exception e) {}
        }
    }
}