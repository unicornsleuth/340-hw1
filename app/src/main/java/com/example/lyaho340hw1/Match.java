package com.example.lyaho340hw1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Match {

    @PrimaryKey
    private String uid;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "name")
    private String name;

    private String username;
    private String imageUrl;
    private int age;
    private String bio;
    private String occupation;
    private double latitude;
    private double longitude;
    private boolean liked;

    public Match() {
        uid = "";
        email = "";
        name = "";
        imageUrl = "";
        username = "";
        age = 0;
        bio = "";
        occupation = "";
        liked = false;
    }

    // ACCESSORS
//    @NonNull
//    public String getUid() { return uid; }
//
//    public String getEmail() { return email; }

    public String getName() { return name; }

//    public String getUsername() { return username; }

    public String getImageUrl() { return imageUrl; }

//    public String getBio() { return bio; }
//
//    public String getOccupation() { return occupation; }
//
//    public int getAge() { return age; }

    // MUTATORS

    public void setLiked(boolean liked) { this.liked = liked; }

//    public void setLongitude(double longitude) { this.longitude = longitude; }
//
//    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setUid(@NonNull String uid) { this.uid = uid; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) { this.name = name; }

//    public void setUsername(String username) { this.username = username; }

    public void setImageUrl(String url) { imageUrl = url; }

//    public void setAge(int age) { this.age = age; }
//
//    public void setBio(String bio) { this.bio = bio; }
//
//    public void setOccupation(String occupation) { this.occupation = occupation; }

}
