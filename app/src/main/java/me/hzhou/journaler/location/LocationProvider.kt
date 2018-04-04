package me.hzhou.journaler.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.util.Log
import me.hzhou.journaler.Journaler
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList


/**
 * Created by hzhou on 4/3/18.
 * Email: hzhou.me@gmail.com
 */
object LocationProvider {
    private val tag = "Location Provider"
    private val listeners = CopyOnWriteArrayList<WeakReference<LocationListener>>()

    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            Log.i(tag, String.format(Locale.ENGLISH, "Location [ lat: %s ][ long: %s ]", location.latitude, location.longitude))
            for (listener in listeners) {
                listener.get()?.onLocationChanged(location)
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            Log.d(tag, String.format(Locale.ENGLISH, "Status changed [ %s ][ %d ]", provider, status))
            for (listener in listeners) {
                listener.get()?.onStatusChanged(provider, status, extras)
            }
        }

        override fun onProviderEnabled(provider: String?) {
            Log.i(tag, String.format("Provider [ %s ][ ENABLED ]", provider))
            for (listener in listeners) {
                listener.get()?.onProviderEnabled(provider)
            }
        }

        override fun onProviderDisabled(provider: String?) {
            Log.i(tag, String.format("Provider [ %s ][ ENABLED ]", provider))
            for (listener in listeners) {
                listener.get()?.onProviderDisabled(provider)
            }
        }

    }

    fun subscribe(subscriber: LocationListener): Boolean {
        val result = doSubscribe(subscriber)
        turnOnLocationListening()
        return result
    }

    fun unsubscribe(subscriber: LocationListener): Boolean {
        val result = doUnsubscribe(subscriber)
        if (listeners.isEmpty()) {
            turnOffLocationListening()
        }
        return result
    }

    private fun turnOnLocationListening() {
        Log.v(tag, "We are about to turn on location listening.")
        val ctx = Journaler.ctx
        if (ctx != null) {
            Log.v(tag, "We are about to check location permissions.")
            val permissionsOK = ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

            if (!permissionsOK) {
                throw IllegalStateException("Permissions required [ ACCESS_FINE_LOCATION ] [ ACCESS_COARSE_LOCATION ]")
            }

            Log.v(tag, "Permissions are OK, and we are about to request location changes")
            val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            criteria.powerRequirement = Criteria.POWER_HIGH
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            criteria.isSpeedRequired = false
            criteria.isCostAllowed = true
            locationManager.requestLocationUpdates(1000, 1F, criteria, locationListener, Looper.getMainLooper())
        } else {
            Log.e(tag, "No application context available.")
        }
    }

    private fun turnOffLocationListening() {
        Log.v(tag, "We are about to turn off location listening.")
        val ctx = Journaler.ctx
        if (ctx != null) {
            val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.removeUpdates(locationListener)
        } else {
            Log.e(tag, "No application context available.")
        }
    }

    private fun doSubscribe(listener: LocationListener): Boolean {
        for (reference in listeners) {
            val refListener = reference.get()
            if (refListener != null && refListener == listener) {
                Log.v(tag, "Already subscribed: $listener")
                return false
            }
        }
        listeners.add(WeakReference(listener))
        Log.v(tag, "Subscribed, subscribers count: " + listeners.size)
        return true
    }

    private fun doUnsubscribe(listener: LocationListener): Boolean {
        var result = true
        for (reference in listeners) {
            val refListener = reference.get()
            if (refListener != null && refListener == listener) {
                val success = listeners.remove(reference)
                if (!success) {
                    Log.w(tag, "Couldn't un subscribe, subscribers count : " + listeners.size)
                } else {
                    Log.v(tag, "Un subscribed, subscribers count: " + listeners.size)
                }
                if (result) {
                    result = success
                }
            }
        }
        return result
    }
}