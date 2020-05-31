package com.example.lyaho340hw1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_settings")
class UserSettings {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "reminder_time")
    private String reminderTime;

    @ColumnInfo(name = "max_distance")
    private int maxDistance;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "looking_for_gender")
    private String lookingForGender;

    @ColumnInfo(name = "private_account")
    private boolean privateAccount;

    @ColumnInfo(name = "min_age")
    private int minAge;

    @ColumnInfo(name = "max_age")
    private int maxAge;

    // CONSTRUCTOR

    public UserSettings() {
        email = "placeholder_email";
        reminderTime = "00:00";
        maxDistance = 100;
        gender = "";
        lookingForGender = "";
        privateAccount = true;
        minAge = 18;
        maxAge = 99; }

    // ACCESSORS

    @NonNull
    public String getEmail() { return email; }
    public String getReminderTime() { return reminderTime; }
    public int getMaxDistance() { return maxDistance; }
    public String getGender() { return gender; }
    public String getLookingForGender() { return lookingForGender; }
    public boolean getPrivateAccount() { return privateAccount; }
    public int getMinAge() { return minAge; }
    public int getMaxAge() { return maxAge; }

    // MUTATORS

    public void setEmail(@NonNull String newEmail) { email = newEmail; }
    public void setReminderTime(String newReminderTime) { reminderTime = newReminderTime; }
    public void setMaxDistance(int newMaxDistance) { maxDistance = newMaxDistance; }
    public void setGender(String newGender) { gender = newGender; }
    public void setLookingForGender(String newLookingForGender) { lookingForGender = newLookingForGender; }
    public void setPrivateAccount(boolean newPrivateAccount) { privateAccount = newPrivateAccount; }
    public void setMinAge(int newMinAge) { minAge = newMinAge; }
    public void setMaxAge(int newMaxAge) { maxAge = newMaxAge; }

}
