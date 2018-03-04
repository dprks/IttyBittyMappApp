package au.com.parkinson.dan.ittybittymappapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.parkinson.dan.ittybittymappapp.MapApplication
import au.com.parkinson.dan.ittybittymappapp.R
import au.com.parkinson.dan.ittybittymappapp.data.network.model.place.PlaceSearchResults
import au.com.parkinson.dan.ittybittymappapp.domain.place.Place
import au.com.parkinson.dan.ittybittymappapp.ui.map.DaggerMapComponent
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapContract
import au.com.parkinson.dan.ittybittymappapp.ui.map.MapPresenterModule
import javax.inject.Inject

class MapActivity : MapContract.View, AppCompatActivity() {

    @Inject
    lateinit var mapPresenter: MapContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        initialisePresenter()
    }

    private fun initialisePresenter() {
        DaggerMapComponent.builder()
                .mapPresenterModule(MapPresenterModule(this))
                .placesRepositoryComponent((application as MapApplication).repositoryComponent)
                .build()
                .inject(this)
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

    override fun showPointsOfInterest(pointsOfInterest: PlaceSearchResults?) {

    }
}
