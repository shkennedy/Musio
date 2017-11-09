package com.sbu.webspotify.domain;

public class GeoLocation {

    private static final int EARTH_RADIUS_KM = 6371;
    private static final double RADIANS_PER_DEGREE = Math.PI / 180.0;

    private float latitude;
    private float longitude;

    public GeoLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public double getDistanceFrom(GeoLocation geoLocation) {
        return distanceInKmBetweenGeoLocations(this, geoLocation);
    }
      
    private static float distanceInKmBetweenGeoLocations(GeoLocation g1, GeoLocation g2) {
        double dLat = (g2.latitude - g1.latitude) * RADIANS_PER_DEGREE;
        double dLon = (g2.longitude - g1.longitude) * RADIANS_PER_DEGREE;
      
        double g1LatRadians = g1.latitude * RADIANS_PER_DEGREE;
        double g2LatRadians = g2.latitude * RADIANS_PER_DEGREE;
      
        double a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) +
                Math.sin(dLon / 2.0) * Math.sin(dLon / 2.0) * Math.cos(g1LatRadians) * Math.cos(g2LatRadians); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); 
     
        return (float)(EARTH_RADIUS_KM * c);
    }
}