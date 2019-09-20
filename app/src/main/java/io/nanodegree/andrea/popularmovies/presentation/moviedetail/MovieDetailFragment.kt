package io.nanodegree.andrea.popularmovies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import io.nanodegree.andrea.popularmovies.R
import kotlinx.android.synthetic.main.detail_activity_content.*
import org.koin.android.viewmodel.ext.android.viewModel
import io.nanodegree.andrea.popularmovies.data.model.Movie


class MovieDetailFragment : Fragment() {

    // Lazy Inject ViewModel
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    /**********************************************************************************************
     * Lifecycle callbacks
     *********************************************************************************************/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movie_detail_fragment, container, false)

        postponeEnterTransition()

        if (arguments?.containsKey(ARG_MOVIE) == true) {
            (arguments?.getSerializable(ARG_MOVIE) as Movie).let {
                ViewCompat.setTransitionName(view.findViewById<View>(R.id.image_iv), it.id)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.containsKey(ARG_MOVIE) == true) {
            (arguments?.getSerializable(ARG_MOVIE) as Movie).let {

                tv_plot_content.text = it.plotSynopsis
                tv_release_date_content.text = it.releaseDate
                tv_rating_content.text = getString(R.string.detail_rating_out_of, it.userRating)

                Picasso.get().load(it.getFormattedImageThumbnailUrl()).into(image_iv)

                // Data is loaded so lets wait for our parent to be drawn
                (view.parent as? ViewGroup)?.doOnPreDraw {
                    // Parent has been drawn. Start transitioning!
                    startPostponedEnterTransition()
                }
            }
        }
    }

    companion object {
        private const val ARG_MOVIE = "movie"

        fun newInstance(movie: Movie): MovieDetailFragment {

            val args = Bundle()
            args.putSerializable(ARG_MOVIE, movie)

            val fragment = MovieDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
