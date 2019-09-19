package java.io.nanodegree.popularmovies.feature.movie.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import io.nanodegree.andrea.popularmovies.feature.movie.R
import kotlinx.android.synthetic.main.detail_activity_content.*
import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie


class MovieDetailFragment : Fragment() {


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments?.containsKey(ARG_MOVIE) == true) {
            (arguments?.getSerializable(ARG_MOVIE) as Movie).let {
                Picasso.get().load(it.getFormattedImageThumbnailUrl()).into(image_iv)
            }
        }
    }
}
