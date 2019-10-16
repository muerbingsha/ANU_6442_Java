package com.anu.samplemap;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EnterActivity extends AppCompatActivity {


    private static final int LOCATION_REQUEST_CODE = 1000;
    private FusedLocationProviderClient mFusedLocationClient;
    private double myLat = 0.0, myLon = 0.0;
    private TextView txtLocation;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        txtLocation = findViewById(R.id.textView);


    }

    public void setLocation(View view) {






        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            System.out.println("location permitted");




            // 1 method 1
            // Initialize the SDK
//            String apiKey = "AIzaSyAWmtG6idRoKm1FA45s7OlYImda0QXHnc4";
//            Places.initialize(getApplicationContext(), apiKey);
//
//            // Create a new Places client instance
//            PlacesClient placesClient = Places.createClient(this);
//
//            // Use fields to define the data types to return.
//            List<Place.Field> placeFields = Collections.singletonList(Place.Field.NAME);
//
//            // Use the builder to create a FindCurrentPlaceRequest.
//            FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);
//
//            // task
//            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
//            placeResponse.addOnCompleteListener(task -> {
//                if (task.isSuccessful()){
//                    FindCurrentPlaceResponse response = task.getResult();
//                    List<PlaceLikelihood> placeLikelihoods = response.getPlaceLikelihoods();
//                    for (PlaceLikelihood p: placeLikelihoods) {
//                        System.out.println(p.getPlace().getName());
//                    }
//
//
//                } else {
//                    Exception exception = task.getException();
//                    if (exception instanceof ApiException) {
//                        ApiException apiException = (ApiException) exception;
//                        Log.e("Shark", "Place not found: " + apiException.getStatusCode());
//                    }
//                }
//            });





            // method 2
            // Construct a FusedLocationProviderClient.
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



            // reqeust
            locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(20 * 1000);

            // callback
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        System.out.println("nothing");
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            System.out.println("good");
                            myLat = location.getLatitude();
                            myLon = location.getLongitude();
//                            txtLocation.setText(String.format(Locale.US, "%s -- %s", myLat, myLon));
                        }
                    }
                }
            };

            // set update
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback,  Looper.myLooper());

            // remove request or it will take resources
            if (mFusedLocationClient != null) {
                mFusedLocationClient.removeLocationUpdates(locationCallback);
            }


            // get current location
            Task<Location> task = mFusedLocationClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null) {
                        //Write your implemenation here
                        Log.d("AndroidClarified",location.getLatitude()+" "+location.getLongitude());

                        // enter map activity


                        Intent intent = new Intent(EnterActivity.this, MapsActivity.class);
                        intent.putExtra(GlobalConstant.LAT.toString(), location.getLatitude());
                        intent.putExtra(GlobalConstant.LON.toString(), location.getLongitude());
                        startActivity(intent);


                        txtLocation.setText(location.getLatitude()+" "+location.getLongitude());
                    }
                }
            });



            // method 3
            // not working
//            this.txtLocation = (TextView) findViewById(R.id.textView);
//            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//            locationRequest = LocationRequest.create();
//            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            locationRequest.setInterval(20 * 1000);
//
//            locationCallback = new LocationCallback() {
//                @Override
//                public void onLocationResult(LocationResult locationResult) {
//                    if (locationResult == null) {
//                        return;
//                    }
//                    for (Location location : locationResult.getLocations()) {
//                        if (location != null) {
//                            wayLatitude = location.getLatitude();
//                            wayLongitude = location.getLongitude();
//                            txtLocation.setText(String.format(Locale.US, "%s -- %s", wayLatitude, wayLongitude));
//                        }
//                    }
//                }
//            };
//
//            if (mFusedLocationClient != null) {
//                mFusedLocationClient.removeLocationUpdates(locationCallback);
//            }



        } else {
            getLocationPermission();
        }
    }



    private void getLocationPermission() {

        // A local method to request required permissions;
        // See https://developer.android.com/training/permissions/requesting
//            getLocationPermission();
        System.out.println("need location permission");

        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);


            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }


    /**
     * get called when
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Good");
//
                    this.txtLocation = (TextView) findViewById(R.id.textView);
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                    locationRequest = LocationRequest.create();
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    locationRequest.setInterval(20 * 1000);

                    locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (locationResult == null) {
                                return;
                            }
                            for (Location location : locationResult.getLocations()) {
                                if (location != null) {
                                    myLat = location.getLatitude();
                                    myLon = location.getLongitude();
                                    txtLocation.setText(String.format(Locale.US, "%s -- %s", myLat, myLon));
                                }
                            }
                        }
                    };

                    if (mFusedLocationClient != null) {
                        mFusedLocationClient.removeLocationUpdates(locationCallback);
                    }

                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
