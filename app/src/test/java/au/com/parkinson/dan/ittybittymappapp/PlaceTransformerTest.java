package au.com.parkinson.dan.ittybittymappapp;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.data.PlaceResultsTransformer;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.sampleData.SampleJSON;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dan on 5/03/2018.
 */

public class PlaceTransformerTest {

    PlaceResultsTransformer placeResultsTransformer;
    PlaceSearchResults inputPlaces;

    @Before
    public void setup() {
        GooglePlaceAdapter placeAdapter = new GooglePlaceAdapter();
        placeResultsTransformer = new PlaceResultsTransformer(placeAdapter);

        Gson gson = new Gson();
        inputPlaces = gson.fromJson(SampleJSON.getPlacesJSON(), PlaceSearchResults.class);
    }

    @Test
    public void testConversion() throws Exception {
        List<Place> outputPlaces = placeResultsTransformer.apply(inputPlaces);
        assertEquals(20, outputPlaces.size());

    }

}
