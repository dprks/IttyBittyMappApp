package au.com.parkinson.dan.ittybittymappapp.data.network;

import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Dagger module provides retrofit Places API service
 *
 * Created by dan on 3/03/2018.
 */
@Module()
public class PlacesAPIModule{

    @Provides
    @Singleton
    PlacesApi provideAPI(Retrofit retrofit) {
        return retrofit.create(PlacesApi.class);
    }
}
