package au.com.parkinson.dan.ittybittymappapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

import au.com.parkinson.dan.ittybittymappapp.data.PlaceResultsTransformer;
import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepository;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.api.PlacesApi;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import io.reactivex.Observable;

/**
 * Created by dan on 3/03/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlacesRepositoryTest {

    private String key = "08971230487120349871230487";
    private String pageToken = "0adsfadsfklfds";

    private String location = "";
    private int radius = 4000;


    @Mock
    PlacesApi apiService;

    PlaceResultsTransformer placeResultsTransformer;

    PlacesRepository placesRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.placeResultsTransformer = new PlaceResultsTransformer(new GooglePlaceAdapter());
        this.placesRepository = new PlacesRepository(apiService, key, placeResultsTransformer);
    }

    @Test
    public void testAPICallFromRepo() throws Exception {

        Observable <ArrayList<Place>> observable = Observable.just(new ArrayList<Place>());

        Mockito.doReturn(observable).when(apiService).getPlacesByLocation(location, radius, pageToken, key);

        placesRepository.getPlacesByLocation(location, radius);

        verify(apiService).getPlacesByLocation(location, radius, pageToken, key);
    }

}
