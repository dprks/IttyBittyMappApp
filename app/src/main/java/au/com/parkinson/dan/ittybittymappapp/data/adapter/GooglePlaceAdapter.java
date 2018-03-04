package au.com.parkinson.dan.ittybittymappapp.data.adapter;

import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;

/**
 * Adapter converts search results from google place api to our domain Place type
 *
 * Created by dan on 4/03/2018.
 */

public class GooglePlaceAdapter implements PlaceAdapter<Result> {

    public Place convert(Result input) {
        Place outputPlace = new Place();
        outputPlace.setId(input.getPlaceId());
        outputPlace.setName(input.getName());

        if(input.getGeometry() != null && input.getGeometry().getLocation() != null) {
            outputPlace.setLatitude(input.getGeometry().getLocation().getLat());
            outputPlace.setLongitude(input.getGeometry().getLocation().getLng());
        }
        return outputPlace;
    }
}
