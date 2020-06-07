package com.example.lyaho340hw1;

import java.beans.PropertyChangeListener;

public class SettingsWrapper {

    private PropertyChangeListener listener;
    private int maxDistance;

    public SettingsWrapper() {
        maxDistance = 100;
    }

    public SettingsWrapper(int max) {
        maxDistance = max;
    }

    public int getMaxDistance() { return maxDistance; }
    public PropertyChangeListener getListener() { return listener; }

    public void setMaxDistance(int max) { maxDistance = max; }
    public void setListener(PropertyChangeListener listener) { this.listener = listener; }
}
