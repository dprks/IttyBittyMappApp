package au.com.parkinson.dan.ittybittymappapp.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;

/**
 * Repository class used to arbitrate fetching of data from one or more sources
 * For purposes of this demo, data will be sourced from API. A local database can also be included in future.
 *
 * Created by dan on 28/02/2018.
 */
@Singleton
public class PlacesRepository {

    @Inject
    public PlacesRepository(PlacesApi api){

    }
}
