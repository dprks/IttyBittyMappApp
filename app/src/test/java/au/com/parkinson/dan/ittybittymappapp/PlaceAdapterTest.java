package au.com.parkinson.dan.ittybittymappapp;

/**
 * Created by dan on 4/03/2018.
 */

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.PlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.sampleData.SampleJSON;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class PlaceAdapterTest {

    private Result inputPlace;
    private Result nullInput;
    private Result nullLocationPlace;

    private PlaceAdapter<Result> placeAdapter;

    @Before
    public void setup() {
        placeAdapter = new GooglePlaceAdapter();

        Gson gson = new Gson();
        inputPlace = gson.fromJson(SampleJSON.getSinglePlaceJSON(), Result.class);
        nullLocationPlace = gson.fromJson(SampleJSON.getSinglePlaceNullLocationJSON(), Result.class);
    }

    @Test
    public void testConversion() {
        Place outputPlace = placeAdapter.convert(inputPlace);
        assertEquals(outputPlace.getId(), "ChIJ90260rVG1moRkM2MIXVWBAQ");
        assertEquals(outputPlace.getName(), "Melbourne");
        assertEquals(outputPlace.getLatitude(), -37.8136276);
        assertEquals(outputPlace.getLongitude(), 144.9630576);
    }

    @Test
    public void testConversionWithNull() {
        Place outputPlace = placeAdapter.convert(nullInput);
        assertNull(outputPlace);
    }

    @Test
    public void testConversionWithNullLocation() {
        Place outputPlace = placeAdapter.convert(nullLocationPlace);
        assertNull(outputPlace);
    }


}
