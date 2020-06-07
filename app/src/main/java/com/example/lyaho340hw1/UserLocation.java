package com.example.lyaho340hw1;

import java.beans.PropertyChangeListener;

public class UserLocation {
    private double latitude;
    private double longitude;
    private boolean hasLat;
    private boolean hasLong;
    private PropertyChangeListener listener;

    public UserLocation() {
        hasLat = false;
        hasLong = false;
        //if (listener != null) listener.propertyChange();
    }

    public UserLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        hasLat = true;
        hasLong = true;
    }

    public boolean isValid() {
        return hasLat && hasLong;
    }

    public PropertyChangeListener getListener() { return listener; }
    public double getLat() { return latitude; }
    public double getLong() { return longitude; }

    public void setListener(PropertyChangeListener listener) { this.listener = listener; }

    public void setLat(double latitude) {
        this.latitude = latitude;
        hasLat = true;
    }
    public void setLong(double longitude) {
        this.longitude = longitude;
        hasLong = true;
    }
}
