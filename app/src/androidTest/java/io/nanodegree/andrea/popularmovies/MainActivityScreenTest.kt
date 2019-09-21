package io.nanodegree.andrea.popularmovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.nanodegree.andrea.popularmovies.presentation.movielist.recyclerview.MovieListAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Basic test class that tests t
 */
@RunWith(AndroidJUnit4::class)
class MainActivityScreenTest {

    private var mIdlingResource: IdlingResource? = null

    @Rule
    @JvmField
    var mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    fun registerIdlingResource() {

        mIdlingResource = mainActivityTestRule.activity.getIdlingResource()
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource)
    }

    @Test
    fun clickViewItem_OpensMovieDetailFragment() {

        //First, scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recycler_view_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieItemViewHolder>(0, click()))

        // Checks that the MovieDetailFragment layout is shown
        onView(withId(R.id.fragment_movie_detail_root)).check(matches(isDisplayed()))
    }

    // Remember to unregister resources when not needed to avoid malfunction.
    @After
    fun unregisterIdlingResource() {

        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource)
        }
    }
}