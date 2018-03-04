package au.com.parkinson.dan.ittybittymappapp.data.network.api;

import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.placeDetails.PlaceDetailsResults;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit API interface for retrieving places from Gooogle's places API
 *
 * API Doc: https://developers.google.com/places/web-service/
 *
 * Created by dan on 28/02/2018.
 */

public interface PlacesApi {

    String BASE_URL = "https://maps.googleapis.com/maps/api/";

    @GET("place/nearbysearch/json")
    Observable<PlaceSearchResults> getPlacesByLocation(@Query("location") String location, @Query("radius") int radius, @Query("key") String apiKey);

    @GET("place/details/json")
    Observable<PlaceDetailsResults> getPlaceDetails(@Query("placeid") String placeId, @Query("key") String apiKey);

}
