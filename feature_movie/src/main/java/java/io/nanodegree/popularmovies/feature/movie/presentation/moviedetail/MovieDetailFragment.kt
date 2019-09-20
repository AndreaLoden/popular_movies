package java.io.nanodegree.popularmovies.feature.movie.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import io.nanodegree.andrea.popularmovies.feature.movie.R
import kotlinx.android.synthetic.main.detail_activity_content.*
import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie


class MovieDetailFragment : Fragment() {

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
