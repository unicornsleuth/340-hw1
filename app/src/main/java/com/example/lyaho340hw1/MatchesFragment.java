package com.example.lyaho340hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class MatchesFragment extends Fragment
        //implements OnChangeSettingsListener
        {

    private MatchViewModel vm;
    private LocationManager locationManager;
    public UserLocation userLocation;
    private ContentAdapter adapter;
    private SettingsWrapper settings;
    private RecyclerView matchRecycler;
    private UserSettingsViewModel userVm;

    private static final String TAG = MatchesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView scrollView = (ScrollView) inflater.inflate(
                R.layout.recycler_view, container, false);
        Context context = getContext();
        matchRecycler = (RecyclerView) scrollView.findViewById(R.id.my_recycler_view);
        vm = new MatchViewModel();
        userVm = new ViewModelProvider(this).get(UserSettingsViewModel.class);

        userLocation = new UserLocation();
        userLocation.setListener(onLocationChange);

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        toggleNetworkUpdates();

        // temporarily set maxDistance to 10
        settings = new SettingsWrapper(10000);
        settings.setListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                SettingsWrapper newSettings = (SettingsWrapper) evt.getNewValue();
                Log.e(TAG, "settings listener called, new dist = " + newSettings.getMaxDistance());
                adapter.filterArrayByDistance(userLocation.getLat(), userLocation.getLong(), newSettings.getMaxDistance());
            }
        });

        adapter = new ContentAdapter(matchRecycler.getContext(), vm, settings, userLocation);
        matchRecycler.setAdapter(adapter);

        Log.d(TAG, "onCreateView invoked");
        return matchRecycler;
}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity rootView = (MainActivity) getActivity();
        //rootView.setOnChangeSettingsListener(this);
        Intent incomingIntent = rootView.getIntent();
        Bundle incomingState = incomingIntent.getExtras();

        if (incomingState != null) {
            Bundle incomingExtras = incomingState.getBundle(Constants.KEY_USER_DATA);

            if (incomingExtras != null) {
                if (incomingExtras.containsKey(Constants.KEY_EMAIL)) {
                    String email = incomingExtras.getString(Constants.KEY_EMAIL);
                    userVm.findSettingsByEmail(email).observe(getViewLifecycleOwner(), new Observer<UserSettings>() {
                        @Override
                        public void onChanged(@androidx.annotation.Nullable final UserSettings userSettingsFromDb) {
                            // Update the cached copy of the settings
                            if (userSettingsFromDb != null) {
                                settings.setMaxDistance(userSettingsFromDb.getMaxDistance());
                                adapter.filterArrayByDistance(userLocation.getLat(), userLocation.getLong(), settings.getMaxDistance());
                                Log.e(TAG, "from Room, maxDistance = " + settings.getMaxDistance());
                            }
                        }
                    });
                }
            }
            Log.d(TAG, "onActivityCreated invoked");
        }
    }

    private final PropertyChangeListener onLocationChange = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            UserLocation newLocation = (UserLocation)evt.getNewValue();
            Log.e("from PropertyChangeListener", "lat: " + newLocation.getLat() + ", long: " + newLocation.getLong());
            adapter.filterArrayByDistance(newLocation.getLat(), newLocation.getLong(), settings.getMaxDistance());
        }
    };

//    @Override
//    public void sendSettings(SettingsWrapper settings) {
//        // what to do when MainActivity sends settings
//        Activity activity = getActivity();
//        if (settings != null) {
//            this.settings.setMaxDistance(settings.getMaxDistance());
//            Log.e(TAG, "settings received from MainActivity: " + settings.getMaxDistance());
//        }
//        adapter.filterArrayByDistance(userLocation.getLat(), userLocation.getLong(), settings.getMaxDistance());
//        Toast.makeText(activity, "showing matches within " + settings.getMaxDistance() + " miles", Toast.LENGTH_LONG).show();
//    }

    // LOCATION

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        Log.e(TAG, "checkLocation invoked");
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        Log.e(TAG, "isLocationEnabled invoked");
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.enable_location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

    // this turns location on/off - set as property to start/stop button (takes View view)
    public void toggleNetworkUpdates() {
        Context context = getContext();
        if(!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListenerNetwork);
            Log.e(TAG, "network provider started running");
        }
        Log.e(TAG, "toggleNetworkUpdates invoked");
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longitudeGPS = location.getLongitude();
            double latitudeGPS = location.getLatitude();
            Activity activity = getActivity();
            activity.runOnUiThread(() -> {
                // Change Display
                userLocation.setLong(longitudeGPS);
                userLocation.setLat(latitudeGPS);
                adapter.filterArrayByDistance(latitudeGPS, longitudeGPS, settings.getMaxDistance());
                Log.e(TAG, "onLocationChanged invoked");
                Toast.makeText(activity, "showing matches within " + settings.getMaxDistance() + " miles", Toast.LENGTH_LONG).show();
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }; // end LocationListener

    @Override
    public void onPause() {
        vm.clear();
        super.onPause();
    }

    // this is for what happens in each "match"
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageButton likeButton;
        public TextView matchName;
        public ViewGroup parent;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            this.parent = parent;
            image = (ImageView) itemView.findViewById(R.id.match_picture);
            likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
            matchName = (TextView) itemView.findViewById(R.id.match_name);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private MatchViewModel vm;
        private ArrayList<Match> matchList;
        private ArrayList<Match> allMatches;

        public ContentAdapter(Context context, MatchViewModel viewModel, SettingsWrapper settings, UserLocation loc) {
            this.vm = viewModel;
            matchList = new ArrayList<>();
            allMatches = new ArrayList<>();
            context.getResources();
            vm.getMatches((ArrayList<Match> matches) -> {
                for (Match match : matches) {
                    if (!allMatches.contains(match)) {
                        allMatches.add(match);
                    }
                    boolean passes = gpsIsWithinDistance(settings.getMaxDistance(), loc.getLat(), loc.getLong(), match);
                    if (matchList.contains(match) && !passes) {
                        matchList.remove(match);
                    } else if (!matchList.contains(match) && passes) {
                        matchList.add(match);
                    }
                }
                Log.e(TAG, "getMatches() invoked");
                notifyDataSetChanged();
            });
        } // end ContentAdapter constructor

        public boolean gpsIsWithinDistance(int distance, double startLat, double startLong, Match match) {
            float[] results = new float[1];
            Location.distanceBetween(startLat, startLong, match.getLatitude(), match.getLongitude(), results);
            results[0] = (float) (results[0]/1609.344);
            Log.e(TAG, results[0] + " <= " + distance + "?");
            return results[0] <= (double) distance;
        }

        public void filterArrayByDistance(double startLat, double startLong, int distance) {
            for (Match match : allMatches) {
                boolean passes = gpsIsWithinDistance(distance, startLat, startLong, match);
                if (matchList.contains(match) && !passes) {
                    matchList.remove(match);
                } else if (!matchList.contains(match) && passes) {
                    matchList.add(match);
                }
            }
            Log.d(TAG, "filterArrayByDistance invoked, dist = " + distance);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //Log.d(TAG, matchList.get(0).getImageUrl());
            Match currentMatch;
            if (matchList != null) {
                currentMatch = matchList.get(position % matchList.size());
                //Log.d(TAG, matchList.get(0).getImageUrl());
                Picasso.get().load(currentMatch.getImageUrl()).into(holder.image);
                holder.matchName.setText(currentMatch.getName());
            } else { currentMatch = new Match(); }
            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = holder.parent.getContext();
                    StringBuilder text;
                    if (holder.likeButton.getContentDescription().equals(context.getString(R.string.liked))) {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_border);
                        holder.likeButton.setContentDescription(context.getString(R.string.not_liked));
                        currentMatch.setLiked(false);
                        text = new StringBuilder("You unliked ");
                    } else {
                        holder.likeButton.setImageResource(R.drawable.ic_favorite_fill);
                        holder.likeButton.setContentDescription(context.getString(R.string.liked));
                        currentMatch.setLiked(true);
                        text = new StringBuilder("You liked ");
                    }

                    text.append(holder.matchName.getText());
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        } // end onBindViewHolder

        @Override
        public int getItemCount() {
            return matchList.size();
        }
    } // end ContentAdapter
}
