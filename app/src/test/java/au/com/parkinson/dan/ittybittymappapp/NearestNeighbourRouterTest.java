package au.com.parkinson.dan.ittybittymappapp;

import com.google.gson.Gson;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.data.PlaceResultsTransformer;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.PlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.PlaceToLatlngAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.Result;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.domain.route.NearestNeighbourRouter;
import au.com.parkinson.dan.ittybittymappapp.sampleData.SampleJSON;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by dan on 5/03/2018.
 */
public class NearestNeighbourRouterTest {

    private LatLng origin = new LatLng(-37.801111, 144.978889);

    private List<LatLng> places;

    private NearestNeighbourRouter router;
    private PlaceResultsTransformer placeResultsTransformer = new PlaceResultsTransformer(new GooglePlaceAdapter());

    @Before
    public void setup() throws Exception{

        Gson gson = new Gson();
        PlaceSearchResults apiResults = gson.fromJson(SampleJSON.getPlacesJSON(), PlaceSearchResults.class);
        places = new PlaceToLatlngAdapter().convert(placeResultsTransformer.apply(apiResults));
    }

    /**
     * Simple test to determine whether the algorithm used results in a shorter path
     */
    @Test
    public void testConversion() {
        router = new NearestNeighbourRouter();

        List<LatLng> originalPath = new ArrayList<>();
        originalPath.add(origin);
        originalPath.addAll(places);
        originalPath.add(origin);

        List<LatLng> routedPath = router.route(origin, places);

        assertTrue(calculateLength(routedPath) <= calculateLength(originalPath));
    }

    private double calculateLength(List<LatLng> path) {
        double length = 0;
        for(int i = 0; i < path.size() - 1; i++) {
            length += path.get(i).distanceTo(path.get(i + 1));
        }
        return length;

    }

}