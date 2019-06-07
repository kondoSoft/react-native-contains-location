
package com.reactlibrary;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import com.google.maps.android.PolyUtil;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import java.util.ArrayList;

public class RNContainsLocationModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNContainsLocationModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNContainsLocation";
  }

  @ReactMethod
  public void containsLocation(ReadableMap point, ReadableArray polygon, Callback completionCallback) {

    LatLng locationPoint = new LatLng(point.getDouble("latitude"), point.getDouble("longitude"));

    List<LatLng> polygonList = new ArrayList<>();

    for (int i = 0; i < polygon.size(); i++) {
      ReadableMap vertex = polygon.getMap(i);
      polygonList.add(new LatLng(vertex.getDouble("latitude"), vertex.getDouble("longitude")));
    }

    boolean isWithinCoverage = PolyUtil.containsLocation(locationPoint, polygonList, false);

    completionCallback.invoke(isWithinCoverage);
  }

}