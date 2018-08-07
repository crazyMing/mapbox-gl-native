package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.testapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timber.log.Timber;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);
    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    List<Feature> features = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      features.add(Feature.fromGeometry(Point.fromLngLat(0, 0)));
    }

    FeatureCollection featureCollection = FeatureCollection.fromFeatures(features);
    mapView.getMapAsync(mapboxMap -> {
      Handler mainHandler = new Handler();

      /*new Thread(() -> {
        String json = featureCollection.toJson();
        mainHandler.post(() -> {
          long time = System.currentTimeMillis();
          GeoJsonSource source = new GeoJsonSource("source2", json);
          mapboxMap.addSource(source);
          SymbolLayer layer = new SymbolLayer("layer2", "source2")
            .withProperties(PropertyFactory.iconImage("restaurant-15"));
          mapboxMap.addLayer(layer);
          Timber.d("BENCHMARK ASYNC: %s", (System.currentTimeMillis() - time));
        });
      }).start();*/

      long time = System.currentTimeMillis();
      GeoJsonSource source = new GeoJsonSource("source", featureCollection);
      mapboxMap.addSource(source);
      SymbolLayer layer = new SymbolLayer("layer", "source")
        .withProperties(PropertyFactory.iconImage("restaurant-15"));
      mapboxMap.addLayer(layer);
      Timber.d("BENCHMARK: %s", (System.currentTimeMillis() - time));
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }
}
