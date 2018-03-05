package au.com.parkinson.dan.ittybittymappapp.domain.route;

import java.util.ArrayList;
import java.util.List;

import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong;

import static au.com.parkinson.dan.ittybittymappapp.domain.place.PlaceMaths.distanceBetween;

/**
 * Creates a reasonably efficient route between points based on a nearest-neighbour algorithm
 * Travelling salesperson problem is hard to solve, so this will do.
 *
 * Created by dan on 4/03/2018.
 */

public class NearestNeighbourRouter implements PlaceRouter <LatLong> {

    @Override
    public List<LatLong> route(LatLong origin, List<? extends LatLong> inputPoints) {

        int locationCount = inputPoints.size();

        ArrayList<LatLong> outputRoute = new ArrayList<>();

        //add origin to path
        outputRoute.add(origin);

        //Add locations to path
        for(int i = 0; i < locationCount; i ++) {
            LatLong nearestNeighbour = getNearestNeighbour(outputRoute.get(i), inputPoints);

            inputPoints.remove(nearestNeighbour);
            outputRoute.add(nearestNeighbour);
        }

        //return to origin
        outputRoute.add(origin);

        return outputRoute;
    }


    private LatLong getNearestNeighbour(LatLong origin, List<? extends LatLong> neighbours) {
        LatLong currentNearest = neighbours.get(0);
        double currentNearness = distanceBetween(origin, neighbours.get(0));

        for(LatLong neighbour : neighbours) {

            double distanceToNeighbour =  distanceBetween(origin, neighbour);

            if(distanceToNeighbour <= currentNearness) {
                currentNearest = neighbour;
                currentNearness = distanceToNeighbour;
            }
        }

        return currentNearest;
    }

}
