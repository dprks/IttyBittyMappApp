package au.com.parkinson.dan.ittybittymappapp;

import android.app.Application;

import au.com.parkinson.dan.ittybittymappapp.data.DaggerPlacesRepositoryComponent;
import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepositoryComponent;
import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepositoryModule;
import au.com.parkinson.dan.ittybittymappapp.data.network.NetModule;
import au.com.parkinson.dan.ittybittymappapp.data.network.PlacesAPIModule;
import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;

/**
 * Created by dan on 3/03/2018.
 */
public class MapApplication extends Application {

    PlacesRepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        repositoryComponent = DaggerPlacesRepositoryComponent.builder()
                .netModule(new NetModule(PlacesApi.BASE_URL))
                .placesRepositoryModule(new PlacesRepositoryModule(BuildConfig.GOOGLE_PLACES_API_KEY))
                .placesAPIModule(new PlacesAPIModule())
                .build();
    }

    public PlacesRepositoryComponent getRepositoryComponent() {
        return repositoryComponent;
    }
}
