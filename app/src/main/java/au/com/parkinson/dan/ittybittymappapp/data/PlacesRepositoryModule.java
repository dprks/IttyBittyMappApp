package au.com.parkinson.dan.ittybittymappapp.data;

import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module provides places repository
 *
 * Created by dan on 3/03/2018.
 */
@Module()
public class PlacesRepositoryModule {

    private String apiKey;

    public PlacesRepositoryModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    PlacesRepository provideRepository(PlacesApi api, PlaceResultsTransformer placeResultsTransformer) {
        return new PlacesRepository(api, apiKey, placeResultsTransformer);
    }
}
