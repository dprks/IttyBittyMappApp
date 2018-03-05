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

    private PlaceResultsTransformer placeResultsTransformer;
    private PlaceSearchResults inputPlaces;

    @Before
    public void setup() {
        GooglePlaceAdapter placeAdapter = new GooglePlaceAdapter();
        placeResultsTransformer = new PlaceResultsTransformer(placeAdapter);

        Gson gson = new Gson();
        inputPlaces = gson.fromJson(SampleJSON.getPlacesJSON(), PlaceSearchResults.class);
    }

    @Test
    public void testConversionOutputSize() throws Exception {
        List<Place> outputPlaces = placeResultsTransformer.apply(inputPlaces);
        assertEquals(20, outputPlaces.size());
    }

    @Test
    public void testConversionOutputSample() throws Exception {
        List<Place> outputPlaces = placeResultsTransformer.apply(inputPlaces);

        assertEquals("ChIJ_7gEN9FC1moRMFOPsdtieeE", outputPlaces.get(1).getId());
        assertEquals("The Best Western Plus Travel Inn Hotel", outputPlaces.get(1).getName());
        assertEquals(-37.8004586, outputPlaces.get(1).getLatitude());
        assertEquals(144.9681022, outputPlaces.get(1).getLongitude());

        assertEquals("ChIJgf0RD69C1moR4OeMIXVWBAU", outputPlaces.get(19).getId());
        assertEquals("Melbourne", outputPlaces.get(19).getName());
        assertEquals(-37.81361100000001, outputPlaces.get(19).getLatitude());
        assertEquals(144.963056, outputPlaces.get(19).getLongitude());
    }

}
