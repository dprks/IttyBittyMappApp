package au.com.parkinson.dan.ittybittymappapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import au.com.parkinson.dan.ittybittymappapp.BuildConfig
import au.com.parkinson.dan.ittybittymappapp.MapApplication
import au.com.parkinson.dan.ittybittymappapp.R
import au.com.parkinson.dan.ittybittymappapp.domain.place.LatLong
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place
import au.com.parkinson.dan.ittybittymappapp.domain.place.Point
import au.com.parkinson.dan.ittybittymappapp.ui.map.DaggerMapComponent
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapContract
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapPresenterModule
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.annotations.PolylineOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_map.*
import javax.inject.Inject

/**
 * MapActivity
 *
 * Maintains a mapbox map, and is fed point and route data from a Presenter.
 */
class MapActivity : MapContract.View, AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var mapPresenter: MapContract.Presenter

    private var mapboxMap: MapboxMap? = null

    //Maps marker IDs to a place object, for click handling
    private var markerMap = HashMap<Long?, Place>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        initialiseMap(savedInstanceState)
        initialisePresenter()
        initialoseFindPlacesButton()
    }

    private fun initialoseFindPlacesButton() {
        findPlacesButton.setOnClickListener { getUserLocation() }
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

        //fetch further details on marker info window click
        this.mapboxMap?.setOnInfoWindowClickListener{onInfoWindowClicked(it)}
        checkLocationPermission()
    }

    /**
     * On marker info window click
     */
    fun onInfoWindowClicked(marker: Marker) : Boolean {
        //TODO implement fetch further details
        return true
    }

    /**
     * Starts the loading process in the presenter
     */
    private fun fetchPlacesForLocation(it: Location) {
        mapPresenter.loadPlaces(Point(it.latitude, it.longitude))
    }

    /**
     * Add marker to show user's current location
     */
    override fun showUserLocation(location: LatLong) {

        mapboxMap?.addMarker(MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                .title("Your Location")
                .icon(IconFactory.getInstance(applicationContext).fromResource(R.drawable.marker_user_location)))
    }

    /**
     * Add markers to show local points of interest
     */
    override fun showPointsOfInterest(pointsToAdd: MutableList<Place>) {

        val pointsOfInterest = ArrayList<LatLng>()

        for (place in pointsToAdd) {
            pointsOfInterest.add(LatLng(place.latitude, place.longitude))

            val marker = mapboxMap?.addMarker(MarkerOptions()
                    .position(LatLng(place.latitude, place.longitude))
                    .title(place.name)
                    .snippet("User rating: " + place.rating))

            markerMap[marker?.id] = place
        }

        zoomToExtent(pointsOfInterest)
    }

    /**
     * Zooms camera to fit all points, with a little padding
     */
    private fun zoomToExtent(pointsOfInterest: ArrayList<LatLng>) {
        mapboxMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(LatLngBounds.Builder().includes(pointsOfInterest).build(), 80))
    }

    /**
     * Add polyline to map to show an efficient path from location to all points and back
     */
    override fun showRoute(route: MutableList<LatLong>) {

        val pointsOfInterest = ArrayList<LatLng>()

        route.mapTo(pointsOfInterest) { LatLng(it.latitude, it.longitude) }

        mapboxMap?.addPolyline(PolylineOptions()
                .addAll(pointsOfInterest)
                .color(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                .width(3f))
    }

    /**
     * Displays point of interest details
     */
    override fun showPointOfInterestDetails(pointOfInterest: Place?) {
        //TODO - use info window click to fetch more detailed information
    }

    override fun clearMap() {
        mapboxMap?.clear()
        markerMap.clear()
    }

    override fun showLoadingIndicator() {
        loadingView?.visibility = View.VISIBLE
    }

    override fun stopLoadingIndicator() {
        loadingView?.visibility = View.GONE
    }

    override fun showEmptySearchResult() {
        stopLoadingIndicator()
        Snackbar.make(containerView, R.string.error_no_places_found, Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorLoading(throwable: Throwable) {
        stopLoadingIndicator()
        Snackbar.make(containerView, R.string.error_loading_places, Snackbar.LENGTH_LONG).show()
    }


    /*
     * Location Permissions
     */
    companion object {
        const val PERMISSION_REQUEST_LOCATION = 1
    }

    /**
     * Check if user has granted permission to access location or show dialog
     */
    fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //Request permission from user
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_LOCATION)

        } else {

            //If permission granted, fetch current location
            showFindPlacesButton()
        }
    }

    private fun showFindPlacesButton() {
        findPlacesButton.visibility = View.VISIBLE
    }

    /**
     * Callback from permission listener
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            showFindPlacesButton()
        }
    }

    /**
     * Fetch user's current location. Requires that user has granted location permission
     */
    @SuppressLint("MissingPermission")
    fun getUserLocation() {

        //Start location provider
        LocationServices.getFusedLocationProviderClient(this)
                ?.lastLocation
                ?.addOnSuccessListener(this, { fetchPlacesForLocation(it) })
    }

}
