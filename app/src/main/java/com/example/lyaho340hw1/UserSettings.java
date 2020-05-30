package com.example.lyaho340hw1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_settings")
class UserSettings {

    @NonNull
    @PrimaryKey
    private String email;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "reminder_time")
    private String reminderTime;

    @ColumnInfo(name = "max_distance")
    private int maxDistance;

    // ACCESSORS

    @NonNull
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getReminderTime() { return reminderTime; }
    public int getMaxDistance() { return maxDistance; }

    // MUTATORS

    public void setEmail(@NonNull String newEmail) { email = newEmail; }
    public void setName(String newName) { name = newName; }
    public void setReminderTime(String newReminderTime) { reminderTime = newReminderTime; }
    public void setMaxDistance(int newMaxDistance) { maxDistance = newMaxDistance; }
}
