package com.rebellion.android.avadrone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashant.singh on 14-May-15.
 */
public class MapActivity extends Activity {

    GoogleMap nMap;

    private List<Marker> markers = new ArrayList<Marker>();

    private Animator animator = new Animator();

    private final Handler mHandler = new Handler();


    Marker courierBoy;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

            if (nMap == null) {
                nMap = ((MapFragment) getFragmentManager()
                        .findFragmentById(R.id.map)).getMap();
            }

        nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        /*Marker m1 = nMap.addMarker(new MarkerOptions().position(new LatLng(28.47190,77.047838 )).title("Marker 1")
                    .snippet("Annual Hackathon Healthkart.com 2015"));

        Marker m2 = nMap.addMarker(new MarkerOptions().position(new LatLng(28.47190,78.047838)).title("Marker 2")
                .snippet("HK+ 2015"));

        Marker m3 = nMap.addMarker(new MarkerOptions().position(new LatLng(29.47190,77.047838)).title("Marker 3")
                .snippet("Motorola 2015"));

*/
        /*nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.47190,77.047838), Float.parseFloat("12")));

        nMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
*/
        registerReceiver(myReceiver, new IntentFilter("MapActivity.action"));
        Location loc = trackLocation();

        Marker userLoc = nMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude())).title("User Location")
                .snippet("You are here"));

        LatLng latLng = new LatLng(loc.getLatitude(),loc.getLongitude());

        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, bearing));


//        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m1, 16.8));

        // Zoom in, animating the camera.
        //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


//        addMarkerToMap(new LatLng(28.483856, 77.078919));
//        addMarkerToMap(new LatLng(28.485855, 77.068470));
//        addMarkerToMap(new LatLng(28.491268, 77.059350));
//        addMarkerToMap(new LatLng(28.484139, 77.053664));
//        addMarkerToMap(new LatLng(28.477953, 77.049007));
//        addMarkerToMap(new LatLng(28.467937, 77.050080));
//        addMarkerToMap(new LatLng(28.463919, 77.050037));
//        addMarkerToMap(new LatLng(28.457731, 77.048771));
//        addMarkerToMap(new LatLng(28.455411, 77.041368));
//        addMarkerToMap(new LatLng(28.462900, 77.036455));
//        addMarkerToMap(new LatLng(loc.getLatitude(),loc.getLongitude()));
//
////        addMarkerToMap(new LatLng(50.958179213755905,3.520646095275879));
////        addMarkerToMap(new LatLng(50.95901719316589,3.5222768783569336));
////        addMarkerToMap(new LatLng(50.95954430150347,3.523542881011963));
////        addMarkerToMap(new LatLng(50.95873336312275,3.5244011878967285));
////        addMarkerToMap(new LatLng(50.95955781702322,3.525688648223877));
////        addMarkerToMap(new LatLng(50.958855004782116,3.5269761085510254));
////
//        animator.startAnimation(true);

    }

    float bearing = Float.parseFloat("15.5");
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //
            Bundle extras = intent.getExtras();
            String latitude = extras.getString("latitude");
            String longitude = extras.getString("longitude");

            int start = 0;
            if(courierBoy != null){
                LatLng prevPosition = courierBoy.getPosition();
                //MarkerOptions marker = new MarkerOptions().position(prevPosition).title("Courier Boy on the way !").snippet("Super man is here !");
                //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.courier_boy_icon));

                addMarkerToMap(prevPosition);

                //if(!latitude.equalsIgnoreCase(String.valueOf(prevPosition.latitude)) || !longitude.equalsIgnoreCase(String.valueOf(prevPosition.longitude))) {

                    LatLng currPosition = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                    //bearing = bearingBetweenLatLngs(prevPosition, currPosition);
                    //if(bearing > 0) {
                         animator.startAnimation(true);
                        // markers.clear();
                        // addMarkerToMap(currPosition);



//                    courierBoy.remove();
//                        marker = new MarkerOptions().position(currPosition).title("Courier Boy -current").snippet("Super man is here !");
//                        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.courier_boy_icon));
//                        courierBoy = nMap.addMarker(marker);



                    //courierBoy = new MarkerOptions().position(currPosition).title("Courier Boy -current").snippet("Super man is here !");
                    //courierBoy.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.courier_boy_icon));

//                        Log.i("ASD -bearing", String.valueOf(bearing));
//
//                    nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currPosition, bearing));
//
//                    Polyline line = nMap.addPolyline(new PolylineOptions()
//                            .add(prevPosition, currPosition)
//                            .width(5)
//                            .color(Color.RED));

                    //trackingMarker.setPosition(intermediatePosition);
                //}
            }
            else {
                //MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).title("Courier Boy").snippet("Super man is here !");
//                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.courier_boy_icon));
//                courierBoy = nMap.addMarker(marker);
//                //ourierBoy = nMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).title("Courier Boy-first").snippet("Super man is here !"));
//                Log.i("ASD -first bearing", String.valueOf(bearing));
//                nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)), bearing));
//                  addMarkerToMap(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
                courierBoy = nMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                        .title("Courier Boy on the way !")
                        .snippet("Super man is here !"));
                courierBoy.setVisible(false);
                markers.add(courierBoy);
            }

            /*nMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    ANIMATE_SPEEED_TURN,
                    new GoogleMap.CancelableCallback() {

                        @Override
                        public void onFinish() {
                        }

                        @Override
                        public void onCancel() {
                        }
                    }
            );*/
//

        }
    };

    private Location convertLatLngToLocation(LatLng latLng) {
        Location location = new Location("someLoc");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        return location;
    }

    private float bearingBetweenLatLngs(LatLng beginLatLng,LatLng endLatLng) {
        Location beginLocation = convertLatLngToLocation(beginLatLng);
        Location endLocation = convertLatLngToLocation(endLatLng);
        Log.i("Begin" , beginLocation.toString());
        Log.i("End" , endLocation.toString());
        return beginLocation.bearingTo(endLocation);
    }

    private Location trackLocation() {
        GPSTracker tracker = new GPSTracker(getApplicationContext());

        Location location = null;
        if(tracker.canGetLocation ){
            location  = tracker.getLocation();
        }else{
            Toast toast = Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG);
            toast.show();
            tracker.showSettingsAlert();
            System.out.println("Please turn on location");
        }
        return location;
    }

    /*private void updatePolyLine(LatLng latLng) {
        List<LatLng> points = polyLine.getPoints();
        points.add(latLng);
        polyLine.setPoints(points);
    }*/

//    @Override
//    protected void onNewIntent(Intent intent) {
//        Bundle extras = intent.getExtras();
//        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
//        // The getMessageType() intent parameter must be the intent you received
//        // in your BroadcastReceiver.
//        String messageType = gcm.getMessageType(intent);
//
//        String mes = extras.getString("data");
//        String[] p = mes.split("~~");
//        String latitude =  p[0].substring(p[0].lastIndexOf("=")+1);
//        String longitude = p[1].substring(p[1].lastIndexOf("=")+1);
//
//        //showToast();
//        nMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude))).title("Courier Boy")
//                .snippet("Super man is here !"));
//        Log.i("GCM", "Received : (" + messageType + ")  " + extras.getString("title"));
//
//        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)), Float.parseFloat("12")));
//
//
//        GcmBroadCastReceiver.completeWakefulIntent(intent);
//    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };

    public class Animator implements Runnable {

        private static final int ANIMATE_SPEEED = 4000;//1500;
        private static final int ANIMATE_SPEEED_TURN = 3000;//1000;
        private static final int BEARING_OFFSET = 20;

        private final Interpolator interpolator = new LinearInterpolator();

        int currentIndex = 0;

        float tilt = 90;
        float zoom = 15.5f;
        boolean upward=true;

        long start = SystemClock.uptimeMillis();

        LatLng endLatLng = null;
        LatLng beginLatLng = null;

        boolean showPolyline = false;

        private Marker trackingMarker;

        public void reset() {
            resetMarkers();
            start = SystemClock.uptimeMillis();
            currentIndex = 0;
            endLatLng = getEndLatLng();
            beginLatLng = getBeginLatLng();

        }

        public void stop() {
            trackingMarker.remove();
            mHandler.removeCallbacks(animator);

        }

        public void initialize(boolean showPolyLine) {
            reset();
            this.showPolyline = showPolyLine;

            highLightMarker(0);

            if (showPolyLine) {
                polyLine = initializePolyLine();
            }

            // We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
            LatLng markerPos = markers.get(0).getPosition();
            LatLng secondPos = markers.get(1).getPosition();

            setupCameraPositionForMovement(markerPos, secondPos);

        }

        private void setupCameraPositionForMovement(LatLng markerPos,
                                                    LatLng secondPos) {

            float bearing = bearingBetweenLatLngs(markerPos,secondPos);

            MarkerOptions markerTest = new MarkerOptions().position(markerPos).title("Courier boy on the way").snippet("Super man is here !");
            markerTest.icon(BitmapDescriptorFactory.fromResource(R.drawable.courier_boy_icon));
            trackingMarker = nMap.addMarker(markerTest);

//            trackingMarker = nMap.addMarker(new MarkerOptions().position(markerPos)
//                    .title("title")
//                    .snippet("snippet"));

            CameraPosition cameraPosition =
                    new CameraPosition.Builder()
                            .target(markerPos)
                            .bearing(bearing + BEARING_OFFSET)
                            .tilt(90)
                            .zoom(nMap.getCameraPosition().zoom >=16 ? nMap.getCameraPosition().zoom : 16)
                            .build();

            nMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    ANIMATE_SPEEED_TURN,
                    new GoogleMap.CancelableCallback() {

                        @Override
                        public void onFinish() {
                            System.out.println("finished camera");
                            animator.reset();
                            Handler handler = new Handler();
                            handler.post(animator);
                        }

                        @Override
                        public void onCancel() {
                            System.out.println("cancelling camera");
                        }
                    }
            );
        }

        private Polyline polyLine;
        private PolylineOptions rectOptions = new PolylineOptions();


        private Polyline initializePolyLine() {
            //polyLinePoints = new ArrayList<LatLng>();
            rectOptions.add(markers.get(0).getPosition());
            return nMap.addPolyline(rectOptions);
        }

        /**
         * Add the marker to the polyline.
         */
        private void updatePolyLine(LatLng latLng) {
            List<LatLng> points = polyLine.getPoints();
            points.add(latLng);
            polyLine.setPoints(points);
        }


        public void stopAnimation() {
            animator.stop();
        }

        public void startAnimation(boolean showPolyLine) {
            if (markers.size()>=1) {
                animator.initialize(showPolyLine);
            }
        }


        @Override
        public void run() {

            long elapsed = SystemClock.uptimeMillis() - start;
            double t = interpolator.getInterpolation((float)elapsed/ANIMATE_SPEEED);

//			LatLng endLatLng = getEndLatLng();
//			LatLng beginLatLng = getBeginLatLng();

            double lat = t * endLatLng.latitude + (1-t) * beginLatLng.latitude;
            double lng = t * endLatLng.longitude + (1-t) * beginLatLng.longitude;
            LatLng newPosition = new LatLng(lat, lng);

            trackingMarker.setPosition(newPosition);

            if (showPolyline) {
                updatePolyLine(newPosition);
            }

            // It's not possible to move the marker + center it through a cameraposition update while another camerapostioning was already happening.
            //navigateToPoint(newPosition,tilt,bearing,currentZoom,false);
            //navigateToPoint(newPosition,false);

            if (t< 1) {
                mHandler.postDelayed(this, 16);
            } else {

                System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + markers.size());
                // imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
                if (currentIndex<markers.size()-2) {

                    currentIndex++;

                    endLatLng = getEndLatLng();
                    beginLatLng = getBeginLatLng();


                    start = SystemClock.uptimeMillis();

                    LatLng begin = getBeginLatLng();
                    LatLng end = getEndLatLng();

                    float bearingL = bearingBetweenLatLngs(begin, end);

                    highLightMarker(currentIndex);

                    CameraPosition cameraPosition =
                            new CameraPosition.Builder()
                                    .target(end) // changed this...
                                    .bearing(bearingL  + BEARING_OFFSET)
                                    .tilt(tilt)
                                    .zoom(nMap.getCameraPosition().zoom)
                                    .build();


                    nMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(cameraPosition),
                            ANIMATE_SPEEED_TURN,
                            null
                    );

                    start = SystemClock.uptimeMillis();
                    mHandler.postDelayed(animator, 16);

                } else {
                    currentIndex++;
                    highLightMarker(currentIndex);
                    stopAnimation();
                }

            }
        }




        private LatLng getEndLatLng() {
            return markers.get(currentIndex+1).getPosition();
        }

        private LatLng getBeginLatLng() {
            return markers.get(currentIndex).getPosition();
        }

        private void adjustCameraPosition() {
            //System.out.println("tilt = " + tilt);
            //System.out.println("upward = " + upward);
            //System.out.println("zoom = " + zoom);
            if (upward) {

                if (tilt<90) {
                    tilt ++;
                    zoom-=0.01f;
                } else {
                    upward=false;
                }

            } else {
                if (tilt>0) {
                    tilt --;
                    zoom+=0.01f;
                } else {
                    upward=true;
                }
            }
        }
    };

    /**
     * Allows us to navigate to a certain point.
     */
    public void navigateToPoint(LatLng latLng,float tilt, float bearing, float zoom,boolean animate) {
        CameraPosition position =
                new CameraPosition.Builder().target(latLng)
                        .zoom(zoom)
                        .bearing(bearing)
                        .tilt(tilt)
                        .build();

        changeCameraPosition(position, animate);

    }

    public void navigateToPoint(LatLng latLng, boolean animate) {
        CameraPosition position = new CameraPosition.Builder().target(latLng).build();
        changeCameraPosition(position, animate);
    }

    private void changeCameraPosition(CameraPosition cameraPosition, boolean animate) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        if (animate) {
            nMap.animateCamera(cameraUpdate);
        } else {
            nMap.moveCamera(cameraUpdate);
        }

    }

    public void toggleStyle() {
        if (GoogleMap.MAP_TYPE_NORMAL == nMap.getMapType()) {
            nMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }


    /**
     * Adds a marker to the map.
     */
    public void addMarkerToMap(LatLng latLng) {
        Marker marker = nMap.addMarker(new MarkerOptions().position(latLng)
                .title("Courier Boy on the way !")
                .snippet("Super man is here !"));
        markers.add(marker);

    }

    /**
     * Clears all markers from the map.
     */
    public void clearMarkers() {
        nMap.clear();
        markers.clear();
    }

    /**
     * Remove the currently selected marker.
     */


    /**
     * Highlight the marker by index.
     */
    private void highLightMarker(int index) {
        highLightMarker(markers.get(index));
    }

    /**
     * Highlight the marker by marker.
     */
    private void highLightMarker(Marker marker) {

		/*
		for (Marker foundMarker : this.markers) {
			if (!foundMarker.equals(marker)) {
				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
			} else {
				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				foundMarker.showInfoWindow();
			}
		}
		*/
        //marker.setIcon(null);
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker.setVisible(false);
        //marker.showInfoWindow();

        //Utils.bounceMarker(googleMap, marker);

    }

    private void resetMarkers() {
        for (Marker marker : this.markers) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            marker.setVisible(false);
            //marker.setIcon(null);
        }
    }

}
