rdt-coin
========
This is a very rapidly developed Android app aiming at consuming the API endpoints available [here](http://coin.melikesit.com/).

* [Setup](#setup)
* [Objectives](#objectives)
* [Features and Highlights](#features-and-highlights)
* [Issues](#issues)

## Setup
To run the app, clone this repository and import it into Android Studio, and hit `Run`.



### Objectives

1. Created a mobile app to consumed the API endpoints
2. Allow user to specify the data amount and time range
3. Allow some interaction between the user and the served data



### Features and Highlights

1. The app implements Model-View-Presenter (MVP) design pattern
2. Implemented date pickers and edit text field to allow user to specify the data point amount and time range
3. Implemented a chart view to allow user to interact with the data (e.g. move around and zoom in/out in both vertical and horizontal direction)
4. Prevented negative time range



### Issues

1. Server doesn't response if the request queries `n=1`
2. Some data parsing is currently being done in the presenter and view level (or main thread) in the app; ideally, those works would be eliminated if the response wraps its data in a object-oriented manner instead of serving them as raw multi-level array.
