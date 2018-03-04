package au.com.parkinson.dan.ittybittymappapp.ui.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepository;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.PlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Map presenter
 *
 * Created by dan on 4/03/2018.
 */
public class MapPresenter implements MapContract.Presenter {

    private CompositeDisposable disposables = new CompositeDisposable();

    private PlaceAdapter<Result> placeAdapter = new GooglePlaceAdapter();

    private PlacesRepository placesRepository;
    private MapContract.View view;

    @Inject
    public MapPresenter(PlacesRepository placesRepository, MapContract.View view) {
        this.placesRepository = placesRepository;
        this.view = view;
    }

    @Override
    public void loadPlaces(Double latitude, Double longitude) {
        Disposable disposable = placesRepository.getPlacesByLocation((latitude + "," + longitude), 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::gotPointsOfInterest);

        disposables.add(disposable);
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
        disposables.clear();
    }

    /**
     * Sink for points of interest. Converts returned results to Places and returns to view.
     *
     * @param searchResults
     */
    private void gotPointsOfInterest(PlaceSearchResults searchResults) {

        List<Result> googlePlaces = searchResults.getResults();

        ArrayList<Place> places = new ArrayList<>();
        for(Result googlePlace : googlePlaces) {
            Place place = placeAdapter.convert(googlePlace);
            if(place != null) {
                places.add(place);
            }
        }

        view.showPointsOfInterest(places);
    }
}
