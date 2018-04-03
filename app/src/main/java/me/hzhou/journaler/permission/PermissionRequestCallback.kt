package me.hzhou.journaler.permission

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
interface PermissionRequestCallback {
    fun onPermissionGranted(permissions: List<String>)
    fun onPermissionDenied(permissions: List<String>)
}