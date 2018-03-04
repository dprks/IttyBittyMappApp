package au.com.parkinson.dan.ittybittymappapp.ui.map;

import javax.inject.Inject;

import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepository;

/**
 * Map presenter
 *
 * Created by dan on 4/03/2018.
 */

public class MapPresenter implements MapContract.Presenter {

    private PlacesRepository placesRepository;
    private MapContract.View view;

    @Inject
    public MapPresenter(PlacesRepository placesRepository, MapContract.View view) {
        this.placesRepository = placesRepository;
        this.view = view;
    }

    @Override
    public void loadPlaces(Double latitude, Double longitude) {

    }

    @Override
    public void loadPlaceDetails(String placeId) {

    }

    @Override
    public void loadRoute() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

}
