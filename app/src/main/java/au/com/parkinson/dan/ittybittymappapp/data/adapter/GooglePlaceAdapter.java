package au.com.parkinson.dan.ittybittymappapp.data.adapter;

import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;

/**
 * Adapter converts search results from google place api to our domain Place type
 * <p>
 * Created by dan on 4/03/2018.
 */

public class GooglePlaceAdapter implements PlaceAdapter<Result> {

    /**
     * @param input google api place result
     * @return Place, or null if input is null or location is null
     */
    public Place convert(Result input) {

        //if place is null, or if location is null, return null
        if (input == null || input.getGeometry() == null || input.getGeometry().getLocation() == null) {
            return null;
        }

        Place outputPlace = new Place();

        outputPlace.setId(input.getPlaceId());
        outputPlace.setName(input.getName());
        outputPlace.setLatitude(input.getGeometry().getLocation().getLat());
        outputPlace.setLongitude(input.getGeometry().getLocation().getLng());

        return outputPlace;
    }
}
