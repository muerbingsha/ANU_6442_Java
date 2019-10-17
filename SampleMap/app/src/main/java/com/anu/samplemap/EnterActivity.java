package com.anu.samplemap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EnterActivity extends AppCompatActivity {


    // Get Current Location
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView txtLocation;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    // GeoFence
    private GeofencingClient geofencingClient;
    private Geofence geofence;
    List geofenceList = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        txtLocation = findViewById(R.id.textView);


        /**
         * geofence
         */
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED  &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {



            // geoFence client
            geofencingClient = LocationServices.getGeofencingClient(this);

            // create one geofence
            geofence = new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId(Constants.GEOFENCE_REQEST_ID) // entry.getKey() can be eventId

                    .setCircularRegion(
                            Constants.SAMPLE_LAT,
                            Constants.SAMPLE_LON,
                            Constants.GEOFENCE_RADIUS_IN_METERS
                    )
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                            Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build();

            // add to list
            geofenceList.add(geofence);


            GeofencingRequest request = new GeofencingRequest.Builder()
                    // Notification to trigger when the Geofence is created
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofences(geofenceList) // add a Geofence
                    .build();


            geofencingClient.addGeofences(request, getGeofencePendingIntent())
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Geofences added
                            // ...

                            System.out.println("Gooood!!!!Gooood!!!!Gooood!!!!");
                            System.out.println("Gooood!!!!");
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to add geofences
                            // ...
                        }
                    });


        } else {
            getBackLocPermission();
        }








        /**
         * get curent location
         */
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
                            Constants.CURRENT_LAT = location.getLatitude();
                            Constants.CURRENT_LON = location.getLongitude();
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

                        Constants.CURRENT_LAT = location.getLatitude();
                        Constants.CURRENT_LON = location.getLongitude();

                        // enter map activity
                        txtLocation.setText(location.getLatitude()+" "+location.getLongitude());
                    }
                }
            });



        } else {
            getLocationPermission();
        }


    }




    /**
     * get pending intent
     */
    private PendingIntent geofencePendingIntent;
    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        geofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }


    /**
     * jump to mapview
     * @param view
     */
    public void setLocation(View view) {
        Intent intent = new Intent(EnterActivity.this, MapsActivity.class);
        startActivity(intent);

    }


    /**
     * code snippet to get location permission
     */
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
                    Constants.LOCATION_REQUEST_CODE);


            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }





    private void getBackLocPermission() {

        // A local method to request required permissions;
        // See https://developer.android.com/training/permissions/requesting
//            getLocationPermission();

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
                    new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    Constants.BACK_LOC_REQUEST_CODE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }

    /**
     * get called when first request permission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case Constants.LOCATION_REQUEST_CODE: {

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

                                    Constants.CURRENT_LAT = location.getLatitude();
                                    Constants.CURRENT_LON = location.getLongitude();

                                    txtLocation.setText(String.format(Locale.US, "%s -- %s", Constants.CURRENT_LAT , Constants.CURRENT_LON ));
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
