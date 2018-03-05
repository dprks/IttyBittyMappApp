package au.com.parkinson.dan.ittybittymappapp.domain.route;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a reasonably efficient route between points based on a nearest-neighbour algorithm
 * Travelling salesperson problem is hard to solve, so this will do.
 *
 * Created by dan on 4/03/2018.
 */

public class NearestNeighbourRouter implements PlaceRouter <LatLng> {

    @Override
    public List<LatLng> route(LatLng origin, List<LatLng> inputPoints) {

        int locationCount = inputPoints.size();

        ArrayList<LatLng> outputRoute = new ArrayList<>();

        //add origin to path
        outputRoute.add(origin);

        //Add locations to path
        for(int i = 0; i < locationCount; i ++) {
            LatLng nearestNeighbour = getNearestNeighbour(outputRoute.get(i), inputPoints);

            inputPoints.remove(nearestNeighbour);
            outputRoute.add(nearestNeighbour);
        }

        //return to origin
        outputRoute.add(origin);

        return outputRoute;
    }


    private LatLng getNearestNeighbour(LatLng origin, List<LatLng> neighbours) {
        LatLng currentNearest = neighbours.get(0);
        double currentNearness = origin.distanceTo(currentNearest);

        for(LatLng neighbour : neighbours) {

            double distanceToNeighbour = origin.distanceTo(neighbour);

            if(distanceToNeighbour <= currentNearness) {
                currentNearest = neighbour;
                currentNearness = distanceToNeighbour;
            }
        }

        return currentNearest;
    }

}
