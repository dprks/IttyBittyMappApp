package au.com.parkinson.dan.ittybittymappapp.ui.map;

import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Presentation module for map feature.
 * Provides concrete implementations of View and Presenter contracts for injection
 */
@Module
public class MapPresenterModule {
    private MapContract.View view;

    public MapPresenterModule(MapContract.View view) {
        this.view = view;
    }

    @Provides
    public MapContract.View provideView() {
        return view;
    }

    @Provides
    public MapContract.Presenter providePresenter(PlacesRepository placesRepository, MapContract.View view) {
        return new MapPresenter(placesRepository, view);
    }
}
