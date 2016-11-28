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
import com.github.gripsack.android.ui.destinations.DestinationAdapter;

import java.util.ArrayList;

public class MapsFragment extends Fragment {
    ArrayList<Trip> trips;
    //ArrayList<Place> places;
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
       /* Trip trip=new Trip();
        trip.setTripName("ABC");
        trips.add(trip);
        trip=new Trip();
        trip.setTripName("BCF");
        trips.add(trip);*/

        mapsAdapter = new MapsAdapter(getActivity(), trips);
        view.setAdapter(mapsAdapter);
        return rootView;
    }

}
