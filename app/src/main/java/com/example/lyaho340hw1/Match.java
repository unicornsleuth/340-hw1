package com.example.lyaho340hw1;

public class Match {

    private String uid;
    private String email;
    private String name;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private boolean liked;

    public Match() {
        uid = "";
        email = "";
        name = "";
        imageUrl = "";
        liked = false;
    }

    // ACCESSORS
//    public String getUid() { return uid; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
//    public boolean getLiked() { return liked; }

    // MUTATORS

    public void setLiked(boolean liked) { this.liked = liked; }
//    public void setLongitude(double longitude) { this.longitude = longitude; }
//    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setUid(String uid) { this.uid = uid; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
//    public void setImageUrl(String url) { imageUrl = url; }

}
