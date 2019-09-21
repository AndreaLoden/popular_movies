package io.nanodegree.andrea.popularmovies

interface HostActivity {
    fun setToolbarTitle(title: String)
    fun getIdlingResource(): SimpleIdlingResource
}