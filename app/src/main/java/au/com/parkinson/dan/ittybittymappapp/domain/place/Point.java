package au.com.parkinson.dan.ittybittymappapp.domain.place;

/**
 * Created by dan on 5/03/2018.
 */

public class Point implements LatLong {

    private final double latitude;
    private final double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }
}
