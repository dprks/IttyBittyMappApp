package au.com.parkinson.dan.ittybittymappapp.ui.map;

import java.util.List;

import javax.inject.Inject;

import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepository;
import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.domain.route.PlaceRouter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Map presenter for managing data loading and formatting for Map Activity.
 *
 * Created by dan on 4/03/2018.
 */
public class MapPresenter implements MapContract.Presenter {

    private CompositeDisposable disposables = new CompositeDisposable();

    private PlacesRepository placesRepository;
    private MapContract.View view;
    private PlaceRouter<LatLong> router;

    @Inject
    public MapPresenter(PlacesRepository placesRepository, MapContract.View view, PlaceRouter<LatLong> router) {
        this.placesRepository = placesRepository;
        this.view = view;
        this.router = router;
    }

    @Override
    public void loadPlaces(LatLong userLocation) {
        view.clearMap();
        view.showLoadingIndicator();

        //Radius is hardcoded to maximum for simplicity
        //TODO change to a different API, as google places will not give us 100 results easily
        Disposable disposable = placesRepository.getPlacesByLocation(userLocation, null, 50000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        places -> {
                            sendResultsToMap(userLocation, places);
                        },
                        view::showErrorLoading);

        disposables.add(disposable);
    }

    private void sendResultsToMap(LatLong userLocation, List<Place> places) {
        if(places == null || places.size() <= 0) {
            view.showEmptySearchResult();

        } else {
            view.showPointsOfInterest(places);
            view.showRoute(getRoute(userLocation, places));
        }

        view.stopLoadingIndicator();
        view.showUserLocation(userLocation);
    }

    @Override
    public void loadPlaceDetails(String placeId) {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        disposables.clear();
    }

    private List<LatLong> getRoute(LatLong origin, List<Place> places) {
        return router.route(origin, places);
    }

}
