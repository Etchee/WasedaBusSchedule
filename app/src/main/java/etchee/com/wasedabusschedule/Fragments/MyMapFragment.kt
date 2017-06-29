package etchee.com.wasedabusschedule.Fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import etchee.com.wasedabusschedule.R
import kotlinx.android.synthetic.main.layout_fragment_map.*
import kotlinx.android.synthetic.main.layout_fragment_map.view.*

/**
 * Fragment to show the routes of the buses
 * Created by rikutoechigoya on 2017/06/28.
 */
class MyMapFragment: Fragment() {

    lateinit var mapInstance:MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.layout_fragment_map, container, false)

        mapInstance = rootView.mapView

        try{
            MapsInitializer.initialize(activity.applicationContext)

            mapInstance.onCreate(savedInstanceState)
        }catch (e:Exception){
            e.printStackTrace()
        }

        mapInstance.getMapAsync { maps ->

            //zoom in relevant place
            val latLng = LatLng(35.708863, 139.719205)
            val cameraPosition = CameraPosition.builder().target(latLng).zoom(14.2f).build()
            val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
            maps.moveCamera(cameraUpdate)

            //add markers
            val latLng_waseda = LatLng(35.708863, 139.719205)
            val wasedaOptions = MarkerOptions()
                    .position(latLng_waseda)
                    .title(resources.getString(R.string.marker_label_waseda))

            val latLng_nishi = LatLng(35.7060997,139.7067037)
            val nishiOptions = MarkerOptions()
                    .position(latLng_nishi)
                    .title(resources.getString(R.string.marker_label_nishi))

            val latLng_twins = LatLng(35.697629, 139.721627)
            val twinsOption = MarkerOptions()
                    .position(latLng_twins)
                    .title(resources.getString(R.string.marker_label_twins))

            maps.addMarker(wasedaOptions); maps.addMarker(nishiOptions); maps.addMarker(twinsOption)

            //check for location permission
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 0)

            }else{
                maps?.isMyLocationEnabled = true
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onPause() {
        super.onPause()
        mapInstance.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapInstance.onResume()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        mapInstance.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapInstance.onLowMemory()
    }
}