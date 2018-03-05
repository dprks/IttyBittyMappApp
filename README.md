# IttyBittyMappApp

This sample is a sample app provided to Itty Bitty apps to meet the following brief:

```
Write an app that will help tourists find sights to see wherever they may be. Use an
appropriate API (e.g., Google Places, Yelp, Foursquare, Flickr, etc) to grab information
about 100 interesting locations around the device’s current location. Plot each location on
a map view. Somehow display information about each location when the user selects the
location.
Beginning at the devices current location, draw a route that connects all the locations
together and ends back at the current location. Don’t bore your tourist by visiting any
location twice. Don’t make your tourist too tired by making the length of the connecting
path any longer than is necessary.
The paths are as the crow flies, don’t worry about routing on roads or other paths.
```

## What it actually does

Due to time constrains and time spent in rabbit-holes, this sample app achieved the following:

1. Fetches the user’s current location
2. Loads up to 20 nearby places of interest from Google’s Places API
3. Plots 20 places on a map box map
4. Creates an efficient route between points using a nearest-neighbour algorithm
5. Displays the point of interest’s name and google rating

### Known limitations
1. Does not fetch 100 places of interest, as google places delivers 20 results at a time, and immediate calls for further pages (as I believe is required) returns an error as explained in https://stackoverflow.com/questions/21265756/paging-on-google-places-api-returns-status-invalid-request. It will only return 60 results total anyway, so I should not have used this service in this way. 
2. Does not fetch place details beyond those retrieved from the place search service call. 

## Reflections on development

I had approached this app as an opportunity to learn how to use some new tools and approaches to coding, and in doing so created an architecture that was arguably superfluous to the demands of the brief. Had I not spent as much time implementing a Dagger framework, I may have had more time to explore other place fetching services. However, I do believe the structure will allow a developer to readily replace this service with another. 

## How to install

### Fetch the repository

Download this repository as a zip, or clone the project to your local machine:

```
$ git://github.com/dprks/IttyBittyMappApp.git
```

Import the Gradle project using Android Studio.
Select a build configuration (both release and debug are currently configured identically) and run to deploy to a device or simulator.

## How to use

### Location Permission
For devices using API 23 and above, runtime location permission will be required. Please allow the application permission to use location services when prompted. 

### Finding places
To start fetching local points of interest, tap the floating action button. This will commence fetching your location, points of interest near to your location, and will calculate a route for you to fly efficiently to all points and back. 

