package au.com.parkinson.dan.ittybittymappapp.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.placeDetails.PlaceDetailsResults;
import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import io.reactivex.Observable;

/**
 * Repository class used to arbitrate fetching of data from one or more sources
 * For purposes of this demo, data will be sourced from API. A local database can also be included in future.
 *
 * Created by dan on 28/02/2018.
 */
@Singleton
public class PlacesRepository {

    private PlacesApi api;
    private String apiKey;
    private PlaceResultsTransformer placeTransformer;

    @Inject
    public PlacesRepository(PlacesApi api, String apiKey, PlaceResultsTransformer placeTransformer){
        this.api = api;
        this.apiKey = apiKey;
        this.placeTransformer = placeTransformer;
    }

    public Observable<List<Place>> getPlacesByLocation(LatLong location, int radius) {
        return api.getPlacesByLocation(String.format("%s,%s", location.getLatitude(), location.getLongitude()), radius, null, apiKey).map(placeTransformer);
    }

    public Observable<PlaceDetailsResults> getPlaceDetails(String placeId) {
        return api.getPlaceDetails(placeId, apiKey);
    }

}
