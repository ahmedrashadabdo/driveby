package thehub.com.drive_by;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mapbox.services.android.BuildConfig;
import com.mapbox.services.android.utils.PermissionsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thehub.com.drive_by.directions.DirectionsV4Activity;
import thehub.com.drive_by.directions.DirectionsV5Activity;
import thehub.com.drive_by.directions.RouteUtilsV5Activity;
import thehub.com.drive_by.geocoding.GeocodingReverseActivity;
import thehub.com.drive_by.geocoding.GeocodingServiceActivity;
import thehub.com.drive_by.geocoding.GeocodingWidgetActivity;
import thehub.com.drive_by.icons.DirectionsIconsActivity;
import thehub.com.drive_by.icons.MakiIconsActivity;
import thehub.com.drive_by.nav.OffRouteDetectionActivity;
import thehub.com.drive_by.staticimage.StaticImageActivity;
import thehub.com.drive_by.turf.TurfBearingActivity;
import thehub.com.drive_by.turf.TurfDestinationActivity;
import thehub.com.drive_by.turf.TurfDistanceActivity;
import thehub.com.drive_by.turf.TurfInsideActivity;
import thehub.com.drive_by.turf.TurfLineSliceActivity;
import thehub.com.drive_by.turf.TurfMidpointActivity;
import thehub.com.drive_by.utils.MapMatchingActivity;
import thehub.com.drive_by.utils.SimplifyPolylineActivity;

/**
 * This activity shows how to use PermissionsUtils to request location permissions
 * from the user. It loads all the sample activities using a RecyclerView.
 */
public class DirectionsActivity extends AppCompatActivity {

  private static final String LOG_TAG = "MainActivity";

  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;

  private static final List<SampleItem> samples = new ArrayList<>(Arrays.asList(
    new SampleItem("Directions v5", "", DirectionsV5Activity.class),
    new SampleItem("Route Utils v5", "", RouteUtilsV5Activity.class),
    new SampleItem("Directions v4", "", DirectionsV4Activity.class),
    new SampleItem("Directions icons", "", DirectionsIconsActivity.class),
    new SampleItem("Reverse geocoding", "", GeocodingReverseActivity.class),
    new SampleItem("Geocoding widget", "", GeocodingWidgetActivity.class),
    new SampleItem("Geocoding service", "", GeocodingServiceActivity.class),
    new SampleItem("Maki icons", "", MakiIconsActivity.class),
    new SampleItem("Static image", "", StaticImageActivity.class),
    new SampleItem("Simplify polyline", "", SimplifyPolylineActivity.class),
    new SampleItem("Map matching", "", MapMatchingActivity.class),
    new SampleItem("Turf bearing", "", TurfBearingActivity.class),
    new SampleItem("Turf destination", "", TurfDestinationActivity.class),
    new SampleItem("Turf distance", "", TurfDistanceActivity.class),
    new SampleItem("Turf line slice", "", TurfLineSliceActivity.class),
    new SampleItem("Turf inside", "", TurfInsideActivity.class),
    new SampleItem("Turf midpoint", "", TurfMidpointActivity.class),
    new SampleItem("Off route detection", "", OffRouteDetectionActivity.class)
  ));

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Debug information
    Log.d(LOG_TAG, "MAS version name: " + BuildConfig.VERSION_NAME);
    Log.d(LOG_TAG, "MAS version code: " + BuildConfig.VERSION_CODE);

    // RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);

    // Use a linear layout manager
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    // Specify an adapter
    adapter = new MainAdapter(samples);
    recyclerView.setAdapter(adapter);

    // Check for location permission
    if (!PermissionsUtils.isLocationGranted(this)) {
      recyclerView.setEnabled(false);
      PermissionsUtils.startPermissionFlow(this);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (PermissionsUtils.isRequestSuccessful(requestCode, permissions, grantResults)) {
      recyclerView.setEnabled(true);
    } else {
      PermissionsUtils.explainFallback(this);
    }
  }

  /*
   * Recycler view
   */

  private class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<SampleItem> samples;

    public class ViewHolder extends RecyclerView.ViewHolder {

      private TextView nameView;
      private TextView descriptionView;

      public ViewHolder(View view) {
        super(view);
        nameView = (TextView) view.findViewById(R.id.nameView);
        descriptionView = (TextView) view.findViewById(R.id.descriptionView);
      }
    }

    public MainAdapter(List<SampleItem> samples) {
      this.samples = samples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_main_feature, parent, false);

      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          int position = recyclerView.getChildLayoutPosition(view);
          Intent intent = new Intent(view.getContext(), samples.get(position).getActivity());
          startActivity(intent);
        }
      });

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.nameView.setText(samples.get(position).getName());
      holder.descriptionView.setText(samples.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
      return samples.size();
    }
  }
}
