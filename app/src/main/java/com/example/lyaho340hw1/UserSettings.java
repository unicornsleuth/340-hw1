package com.example.lyaho340hw1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_settings")
class UserSettings implements Parcelable {

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
        privateAccount = true; }

    // ACCESSORS

    protected UserSettings(Parcel in) {
        email = in.readString();
        reminderTime = in.readString();
        maxDistance = in.readInt();
        gender = in.readString();
        lookingForGender = in.readString();
        privateAccount = in.readByte() != 0;
        minAge = in.readInt();
        maxAge = in.readInt();
    }

    public static final Creator<UserSettings> CREATOR = new Creator<UserSettings>() {
        @Override
        public UserSettings createFromParcel(Parcel in) {
            return new UserSettings(in);
        }

        @Override
        public UserSettings[] newArray(int size) {
            return new UserSettings[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0; // STUB
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // STUB
        dest.writeString(email);
        dest.writeString(reminderTime);
        dest.writeInt(maxDistance);
        dest.writeString(gender);
        dest.writeString(lookingForGender);
        dest.writeByte((byte) (privateAccount ? 1 : 0));
        dest.writeInt(minAge);
        dest.writeInt(maxAge);
    }
}
