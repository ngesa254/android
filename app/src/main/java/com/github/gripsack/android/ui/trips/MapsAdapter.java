package com.github.gripsack.android.ui.trips;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.gripsack.android.R;
import com.github.gripsack.android.data.model.DirectionsJSONParser;
import com.github.gripsack.android.data.model.Trip;
import com.github.gripsack.android.utils.MapUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tuze on 11/28/16.
 */

public class MapsAdapter extends
        RecyclerView.Adapter<MapsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tvTripName)
        TextView tvTripName;
        @BindView(R.id.tvTripDate)
        TextView tvTripDate;
        @BindView(R.id.btnDetail)
        Button btnDetail;
        @BindView(R.id.map)
        MapView map;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position

        }
    }

    private static List<Trip> mTrips;
    private static Context mContext;

    public MapsAdapter(Context context, List<Trip> trips) {
        mTrips = trips;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }
    @Override
    public MapsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View destinationsView = inflater.inflate(R.layout.widget_cardview_map, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(destinationsView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MapsAdapter.ViewHolder viewHolder, int position) {

        Trip trip = mTrips.get(position);
        viewHolder.tvTripName.setText(trip.getTripName());
        viewHolder.tvTripDate.setText(trip.getBeginDate());

        viewHolder.map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng sf = new LatLng(37.773972, -122.431297);
                googleMap.addMarker(new MarkerOptions().position(sf).title("Marker")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sf));
            }

        });

    }
    @Override
    public int getItemCount() {
        return mTrips.size();
    }
}
