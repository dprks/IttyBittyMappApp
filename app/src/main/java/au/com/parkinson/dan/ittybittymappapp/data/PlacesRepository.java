package au.com.parkinson.dan.ittybittymappapp.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.placeDetails.PlaceDetailsResults;
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

    @Inject
    public PlacesRepository(PlacesApi api, String apiKey){
        this.api = api;
        this.apiKey = apiKey;
    }

    public Observable<PlaceSearchResults> getPlacesByLocation(String location, int radius) {
        return api.getPlacesByLocation(location, radius, apiKey);
    }

    public Observable<PlaceDetailsResults> getPlaceDetails(String placeId) {
        return api.getPlaceDetails(placeId, apiKey);
    }

}
