package au.com.parkinson.dan.ittybittymappapp.domain.route;

import java.util.List;

/**
 * Generic interface for creating routes between places.
 * This can be implemented to find different solutions to the travelling salesperson problem
 *
 * Created by dan on 3/03/2018.
 */

public interface PlaceRouter <T> {

    /**
     * @param origin the initial point
     * @param inputPoints a list of points to route
     * @return the ordered output route
     */
    List<T> route(T origin, List<T> inputPoints);
}
