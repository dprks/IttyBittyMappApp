package au.com.parkinson.dan.ittybittymappapp.ui.map;

import au.com.parkinson.dan.ittybittymappapp.data.PlacesRepositoryComponent;
import au.com.parkinson.dan.ittybittymappapp.di.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {MapPresenterModule.class}, dependencies = {PlacesRepositoryComponent.class})
public interface MapComponent {
  void inject(MapActivity mapActivity);
}
