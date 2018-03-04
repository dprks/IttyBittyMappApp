package au.com.parkinson.dan.ittybittymappapp.ui.map;

import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
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

    void showPointsOfInterest(PlaceSearchResults pointsOfInterest);

    void showPointOfInterestDetails(Place pointOfInterest);

    void showRoute(List<Place> route);

    void clearMap();

    void stopLoadingIndicator();

    void showEmptySearchResult();

    void showErrorLoading();
  }

  interface Presenter extends BasePresenter {

    /**
     * load places of interest around user's location
     */
    void loadPlaces(Double latitude, Double longitude);

    void loadPlaceDetails(String placeId);

    void loadRoute();

  }

}
