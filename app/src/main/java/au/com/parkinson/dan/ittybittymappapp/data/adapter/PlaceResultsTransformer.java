package au.com.parkinson.dan.ittybittymappapp.data.adapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import io.reactivex.functions.Function;

/**
 * Transformer used to map a PlaceSearchResults object returned by Google Places API to a list of Places
 *
 * TODO refactor such that generic PlaceAdapter can be passed in to generalise this transformer
 *
 * Created by dan on 5/03/2018.
 */
public class PlaceResultsTransformer implements Function<PlaceSearchResults, List<Place>> {

    private GooglePlaceAdapter placeAdapter;

    @Inject
    public PlaceResultsTransformer(@NonNull GooglePlaceAdapter placeAdapter) {
        this.placeAdapter = placeAdapter;
    }

    @Override
    public List<Place> apply(PlaceSearchResults searchResults) throws Exception {
        List<Result> googlePlaces = searchResults.getResults();

        ArrayList<Place> places = new ArrayList<>();
        for (Result googlePlace : googlePlaces) {
            Place place = placeAdapter.convert(googlePlace);
            if (place != null) {
                places.add(place);
            }
        }
        return places;
    }
}