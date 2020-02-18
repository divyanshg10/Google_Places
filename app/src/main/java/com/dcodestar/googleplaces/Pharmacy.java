package com.dcodestar.googleplaces;

public class Pharmacy {
    final String id;
    final String name;
    final String vicinity;
    final double userRating;

    public Pharmacy(String id, String name, String vicinity, double userRating) {
        this.id = id;
        this.name = name;
        this.vicinity = vicinity;
        this.userRating = userRating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public double getUserRating() {
        return userRating;
    }
}
