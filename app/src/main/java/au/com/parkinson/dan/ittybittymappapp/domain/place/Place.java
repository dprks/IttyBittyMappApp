package au.com.parkinson.dan.ittybittymappapp.domain.place;

/**
 * Domain level model for a place.
 *
 * Created by dan on 3/03/2018.
 */

public class Place {

    private String id;
    private Double latitude;
    private Double longitude;
    private String name;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
