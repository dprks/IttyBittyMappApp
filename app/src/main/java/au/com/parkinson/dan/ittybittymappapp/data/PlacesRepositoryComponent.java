package au.com.parkinson.dan.ittybittymappapp.data;

import java.util.List;

import javax.inject.Singleton;

import au.com.parkinson.dan.ittybittymappapp.data.network.NetModule;
import au.com.parkinson.dan.ittybittymappapp.data.network.PlacesAPIModule;
import dagger.Component;

/**
 * Dagger component interface for injecting PlacesRepository
 *
 * Created by dan on 3/03/2018.
 */
@Singleton
@Component(modules = {PlacesAPIModule.class, NetModule.class})
public interface PlacesRepositoryComponent {

    PlacesRepository getPlacesRepository();

}
