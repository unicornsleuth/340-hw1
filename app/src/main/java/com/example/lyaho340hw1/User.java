package com.example.lyaho340hw1;

public class User {

    private String email;
    private String name;
    private String username;
    private String photoURL;
    private int age;
    private String bio;
    private String occupation;

    public User() {
        email = "";
        name = "";
        photoURL = "";
        username = "";
        age = 0;
        bio = "";
        occupation = "";
    }

    public User(String email, String name, String username) {
        this.email = email;
        this.name = name;
        this.username = username;
    }

    public User(String email, String name, String username, String photoUrl, String bio, String occupation, int age) {
        this(email, name, username);
        this.photoURL = photoUrl;
        this.bio = bio;
        this.occupation = occupation;
        this.age = age;
    }

    // ACCESSORS

    public String getEmail() { return email; }

    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getPhotoURL() { return photoURL; }

    public String getBio() { return bio; }

    public String getOccupation() { return occupation; }

    public int getAge() { return age; }

    // MUTATORS

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) { this.name = name; }

    public void setUsername(String username) { this.username = username; }

    public void setPhotoURL(String url) { photoURL = url; }

    public void setAge(int age) { this.age = age; }

    public void setBio(String bio) { this.bio = bio; }

    public void setOccupation(String occupation) { this.occupation = occupation; }
}
