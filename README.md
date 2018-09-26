
# react-native-contains-location

## Getting started

`$ npm install react-native-contains-location --save`

### Manual installation

#### iOS

1. Go to the ios folder and create a Podfile `pod init`, if you don't have cocoapods installed `sudo gem install cocoapods`
2. Add this code in you're Podfile
```ruby
# Uncomment the next line to define a global platform for your project
platform :ios, '9.0'

target 'YOURE_APP_NAME' do
  # Uncomment the next line if you're using Swift or would like to use dynamic frameworks
  # use_frameworks!

  # Pods for contains
  pod 'React', :path => '../node_modules/react-native', :subspecs => [
    'Core',
    'CxxBridge', # Include this for RN >= 0.47
    'DevSupport', # Include this to enable In-App Devmenu if RN >= 0.43
    'RCTText',
    'RCTNetwork',
    'RCTWebSocket', # Needed for debugging
    'RCTAnimation', # Needed for FlatList and animations running on native UI thread
    # Add any other subspecs you want to use in your project
  ]
  # Explicitly include Yoga if you are using RN >= 0.42.0
  pod 'yoga', :path => '../node_modules/react-native/ReactCommon/yoga'

  # Third party deps podspec link
  pod 'DoubleConversion', :podspec => '../node_modules/react-native/third-party-podspecs/DoubleConversion.podspec'
  pod 'glog', :podspec => '../node_modules/react-native/third-party-podspecs/glog.podspec'
	pod 'Folly', :podspec => '../node_modules/react-native/third-party-podspecs/Folly.podspec'
	# THE PATH TO INSTALL DEPENDENCIES FROM THE MODULE
  pod 'RNContainsLocation', path: '../node_modules/react-native-contains-location'
end
```
3. Now you need to open the `.xcworkspace` inside the ios folder, clean the project and then build and restart you're server `npm start --reset-cache`

#### NOTES:

IN CASE OF THE XCODE SHOW THIS ERROR `<GoogleMaps/GMSGeometryUtils.h> not found`
restart you're machine or close the xcode and open again and try again.

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNContainsLocationPackage;` to the imports at the top of the file
  - Add `new RNContainsLocationPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-contains-location'
  	project(':react-native-contains-location').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-contains-location/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-contains-location')
  	```

## Usage
```javascript
import { containsLocation } from 'react-native-contains-location';

var polygon = [
	{ lat: 3.1336599385978805, lng: 101.31866455078125 },
	{ lat: 3.3091633559540123, lng: 101.66198730468757 },
	{ lat: 3.091150714460597,  lng: 101.92977905273438 },
	{ lat: 2.7222113428196213, lng: 101.74850463867188 },
	{ lat: 2.7153526167685347, lng: 101.47933959960938 },
	{ lat: 3.1336599385978805, lng: 101.31866455078125 }
]
navigator.geolocation.getCurrentPosition(
    (position) => {
      let point = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      }
      containsLocation(point, polygon)
	.then(response => {
		// if the promise was success return TRUE
		console.log('Im inside of the polygon?', response)
	})
	.catch(error => {
		//only return FALSE if the point is not inside of the polygon
		console.log('Im inside of the polygon?', error)
	})
    },
    (error) => alert(error.message),
    { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 }
  )
```
  