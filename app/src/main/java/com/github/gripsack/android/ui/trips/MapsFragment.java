package com.github.gripsack.android.ui.trips;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gripsack.android.R;
import com.github.gripsack.android.data.model.Place;
import com.github.gripsack.android.data.model.Trip;
import com.github.gripsack.android.data.model.User;
import com.github.gripsack.android.ui.destinations.DestinationAdapter;
import com.github.gripsack.android.utils.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsFragment extends Fragment {
    ArrayList<Trip> trips;
    MapsAdapter mapsAdapter;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trips_upcoming, container, false);
        RecyclerView view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(getActivity()));

        trips=new ArrayList<Trip>();
        //getTripsData();

        mapsAdapter = new MapsAdapter(getActivity(), trips);
        view.setAdapter(mapsAdapter);
        return rootView;
    }

  /*  private void getTripsData(){
        Trip trip=new Trip();
        trip.setTripName("San Francisco Trip");
        trip.setBeginDate("11-30-2016");
        Place place=new Place();
        place.setPhotoUrl("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyCcMbLMaGDqEElvOqf3Ql0J9GZvuTr9LZA&photoreference=CoQBdwAAAFRBzZNFQ3gcF7BeFSrOPW6dCRL16rn7eLQOc6VpBNCAK13PLkOyiCiBvnms7m5pk_gzfMDDaElEuZKq4zRZDiDL9mRZFrho3T6Z6Bgqm9fp3zd4izGephdlRh3b2GnFrMkZc10bxzrt1dcshQtaxTj8gr4LovdOi2dKdGJyOIZ1EhApwxBOdCiFNF0Yl74i1Gd3GhSyinKjAlDnihZfPU2E8zCmWyHFJw");
        place.setName("San Francisco");
        place.setLongitude(-122.431297);
        place.setLatitude(37.773972);
        trip.setSearchDestination(place);

        trips.add(trip);

        trip=new Trip();
        trip.setTripName("Bay Area Trip");
        trip.setBeginDate("11-30-2016");
        place=new Place();
        place.setPhotoUrl("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyCcMbLMaGDqEElvOqf3Ql0J9GZvuTr9LZA&photoreference=CoQBdwAAALQYbTOiipsxvsIbm9Feij6cQT-VnhY8rl1JoOrzdjy3r3-KIvhlmUwxJa3L7qfhRfIBi-PhPj3-f4zal9SyTI7STON3GkZXy82XMMaVEFjJ-aSWT-gw45GPpifFp8Val01jyT3MAUT8GSCI2zp8XMpd3Q82p9zMAQ0x7HWlGCFPEhCsjHrfq1pXEQ3JnkSPhw4QGhRAMG261oGsIE8NlqLE7hHgDx9yvQ");
        place.setLongitude(-122.4056395);
        place.setLatitude(37.7785189);
        trip.setSearchDestination(place);
        trips.add(trip);
    }*/

}
