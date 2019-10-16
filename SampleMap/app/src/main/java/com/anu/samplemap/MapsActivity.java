package com.anu.samplemap;

import androidx.fragment.app.FragmentActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double currentLat;
    private double currentLon;
    private SearchView search;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // get data
        Intent intent = getIntent();
        currentLat  = intent.getDoubleExtra(GlobalConstant.LAT.toString(), -1);
        currentLon = intent.getDoubleExtra(GlobalConstant.LON.toString(), -1);





        // get map API key
        String apiKey = getString(R.string.google_maps_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // get the fragment
        AutocompleteSupportFragment autoFrag = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_auto);

        autoFrag.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autoFrag.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }



    static String TAG = "Cool";



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

        // Add a marker in current location and move the camera
        LatLng currentPlace = new LatLng(currentLat, currentLon);
        mMap.addMarker(new MarkerOptions()
                .position(currentPlace)
                .title("Marker in Sydney"));

        // I don't want zoom
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPlace,15)); // move camera to current location and zoom
//        mMap.animateCamera(CameraUpdateFactory.zoomTo( 15.0f ) );
    }
}
