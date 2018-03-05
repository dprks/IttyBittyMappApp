package au.com.parkinson.dan.ittybittymappapp;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.data.PlaceResultsTransformer;
import au.com.parkinson.dan.ittybittymappapp.data.adapter.GooglePlaceAdapter;
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults;
import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;
import au.com.parkinson.dan.ittybittymappapp.domain.place.Point;
import au.com.parkinson.dan.ittybittymappapp.domain.route.NearestNeighbourRouter;
import au.com.parkinson.dan.ittybittymappapp.sampleData.SampleJSON;

import static au.com.parkinson.dan.ittybittymappapp.domain.place.PlaceMaths.distanceBetween;
import static org.junit.Assert.assertTrue;

/**
 * Created by dan on 5/03/2018.
 */
public class NearestNeighbourRouterTest {

    private LatLong origin = new Point(-37.801111, 144.978889);

    private List<Place> places;

    private NearestNeighbourRouter router;

    private PlaceResultsTransformer placeResultsTransformer = new PlaceResultsTransformer(new GooglePlaceAdapter());

    @Before
    public void setup() throws Exception{

        Gson gson = new Gson();
        PlaceSearchResults apiResults = gson.fromJson(SampleJSON.getPlacesJSON(), PlaceSearchResults.class);
        places = placeResultsTransformer.apply(apiResults);
    }

    /**
     * Simple test to determine whether the algorithm used results in a shorter path
     */
    @Test
    public void testConversion() {
        router = new NearestNeighbourRouter();

        List<LatLong> originalPath = new ArrayList<>();
        originalPath.add(origin);
        originalPath.addAll(places);
        originalPath.add(origin);

        List<LatLong> routedPath = router.route(origin, places);

        assertTrue(calculateLength(routedPath) <= calculateLength(originalPath));
    }

    private double calculateLength(List<LatLong> path) {
        double length = 0;
        for(int i = 0; i < path.size() - 1; i++) {
            length += distanceBetween(path.get(i), path.get(i + 1));
        }
        return length;
    }



}