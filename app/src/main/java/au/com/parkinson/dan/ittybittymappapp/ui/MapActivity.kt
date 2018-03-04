package au.com.parkinson.dan.ittybittymappapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.parkinson.dan.ittybittymappapp.BuildConfig
import au.com.parkinson.dan.ittybittymappapp.MapApplication
import au.com.parkinson.dan.ittybittymappapp.R
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place
import au.com.parkinson.dan.ittybittymappapp.ui.map.DaggerMapComponent
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapContract
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapPresenterModule
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_map.*
import javax.inject.Inject

class MapActivity : MapContract.View, AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var mapPresenter: MapContract.Presenter

    private var mapboxMap: MapboxMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        initialiseMap(savedInstanceState)
        initialisePresenter()
    }

    private fun initialiseMap(savedInstanceState: Bundle?) {
        Mapbox.getInstance(this, BuildConfig.MAPBOX_API_KEY)

        mapView!!.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    private fun initialisePresenter() {
        DaggerMapComponent.builder()
                .mapPresenterModule(MapPresenterModule(this))
                .placesRepositoryComponent((application as MapApplication).repositoryComponent)
                .build()
                .inject(this)
    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        this.mapboxMap = mapboxMap
        mapPresenter.loadPlaces(-37.801111, 144.978889)
    }

    override fun showPointOfInterestDetails(pointOfInterest: Place?) {

    }

    override fun showRoute(route: MutableList<Place>?) {

    }

    override fun clearMap() {

    }

    override fun stopLoadingIndicator() {

    }

    override fun showEmptySearchResult() {

    }

    override fun showErrorLoading() {

    }

    override fun showPointsOfInterest(pointsToAdd: MutableList<Place>) {
        for(place in pointsToAdd) {
            mapboxMap?.addMarker(MarkerOptions()
                    .position(LatLng(place.latitude, place.longitude))
                    .title(place.name)
                    .snippet(place.name))
        }
    }
}
