package au.com.parkinson.dan.ittybittymappapp.data.adapter;

import au.com.parkinson.dan.ittybittymappapp.domain.place.Place;

/**
 * Generic adapter interface to convert known types to Place
 *
 * Created by dan on 4/03/2018.
 */

public interface PlaceAdapter <T> {
    Place convert(T input);
}
