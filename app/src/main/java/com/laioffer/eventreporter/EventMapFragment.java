package com.laioffer.eventreporter;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventMapFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener{

    private static final int DISTANCE = 50;
    private MapView mMapView;
    private View mView;
    private DatabaseReference database;
    private List<Event> events;
    private GoogleMap mGoogleMap;
    private Marker lastClicked;

    public EventMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_event_map, container,
                false);
        database = FirebaseDatabase.getInstance().getReference();
        events = new ArrayList<Event>();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.event_map_view);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();// needed to get the map to display immediately
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        mGoogleMap.setOnInfoWindowClickListener(this);

        final LocationTracker locationTracker = new LocationTracker(getActivity());
        locationTracker.getLocation();

        double curLatitude = locationTracker.getLatitude();
        double curLongitude = locationTracker.getLongitude();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(curLatitude, curLongitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        setUpMarkersCloseToCurLocation(googleMap, curLatitude, curLongitude);

        mGoogleMap.setOnMarkerClickListener(this);
    }

    private void setUpMarkersCloseToCurLocation(final GoogleMap googleMap,
                                                final double curLatitude,
                                                final double curLongitude) {
        events.clear();
        database.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get all available events
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Event event = noteDataSnapshot.getValue(Event.class);
                    double destLatitude = event.getLatitude();
                    double destLongitude = event.getLongitude();
                    int distance = Utils.distanceBetweenTwoLocations(curLatitude, curLongitude,
                            destLatitude, destLongitude);
                    if (distance <= DISTANCE) {
                        events.add(event);
                    }
                }

                // Set up every events
                for (Event event : events) {
                    // create marker
                    MarkerOptions marker = new MarkerOptions().position(
                            new LatLng(event.getLatitude(), event.getLongitude())).
                            title(event.getTitle());

                    // Changing marker icon
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                    // adding marker
                    Marker mker = googleMap.addMarker(marker);
                    mker.setTag(event);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: do something
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Event event = (Event)marker.getTag();
        Intent intent = new Intent(getContext(), CommentActivity.class);
        String eventId = event.getId();
        intent.putExtra("EventID", eventId);
        getContext().startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        final Event event = (Event)marker.getTag();
        if (lastClicked != null && lastClicked.equals(marker)) {
            lastClicked = null;
            marker.hideInfoWindow();
            marker.setIcon(null);
            return true;
        } else {
            lastClicked = marker;
            new AsyncTask<Void, Void, Bitmap>(){
                @Override
                protected Bitmap doInBackground(Void... voids) {
                    Bitmap bitmap = Utils.getBitmapFromURL(event.getImgUri());
                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap  bitmap) {
                    super.onPostExecute(bitmap);
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                    marker.setTitle(event.getTitle());
                }
            }.execute();
            return false;
        }
    }

}
