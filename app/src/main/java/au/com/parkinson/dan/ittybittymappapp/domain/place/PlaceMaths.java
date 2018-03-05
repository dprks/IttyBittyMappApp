package au.com.parkinson.dan.ittybittymappapp.domain.place;

/**
 * Created by dan on 5/03/2018.
 */

public class PlaceMaths {

    public static double distanceBetween(LatLong origin, LatLong destination) {
        double divLat = origin.getLatitude() - destination.getLatitude();
        double divLong = origin.getLongitude() - destination.getLongitude();
        return Math.sqrt(Math.pow(divLat, 2) + Math.pow(divLong, 2));
    }

}
