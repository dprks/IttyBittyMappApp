package au.com.parkinson.dan.ittybittymappapp.ui.map;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.ui.presentation.BasePresenter;
import au.com.parkinson.dan.ittybittymappapp.ui.presentation.BaseView;

/**
 * Contract for interaction between map view and map presenter
 *
 * Created by dan on 4/03/2018.
 */
public interface MapContract {

  interface View extends BaseView {

    void showUserLocation(LatLong latLng);

    void showPointsOfInterest(List<Place> pointsToAdd);

    void showPointOfInterestDetails(Place pointOfInterest);

    void showRoute(List<LatLong> route);

    void clearMap();

    void stopLoadingIndicator();

    void showEmptySearchResult();

    void showErrorLoading(Throwable throwable);
  }

  interface Presenter extends BasePresenter {

    /**
     * load places of interest around user's location
     */
    void loadPlaces(LatLong userLocation);

    void loadPlaceDetails(String placeId);

    void loadRoute();

  }

}
