package io.nanodegree.andrea.popularmovies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import io.nanodegree.andrea.popularmovies.R
import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.extensions.observe
import io.nanodegree.andrea.popularmovies.presentation.moviedetail.recyclerview.ReviewsAdapter
import io.nanodegree.andrea.popularmovies.presentation.moviedetail.recyclerview.TrailersAdapter
import kotlinx.android.synthetic.main.detail_fragment_content.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {

    private var trailersAdapter: TrailersAdapter? = null
    private var reviewsAdapter: ReviewsAdapter? = null

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

                observe(movieDetailViewModel.stateLiveData, ::onStateChange)
                movieDetailViewModel.loadRelatedData(it.id ?: "")

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

        context?.let {
            reviewsAdapter = ReviewsAdapter(it)
            tv_reviews_list.adapter = reviewsAdapter
            tv_reviews_list.layoutManager = LinearLayoutManager(it)
            
            trailersAdapter = TrailersAdapter(it)
            tv_trailers_list.adapter = trailersAdapter
            tv_trailers_list.layoutManager = LinearLayoutManager(it)
        }
    }

    private fun onStateChange(state: MovieDetailViewModel.ViewState) {

        if (state.reviews.isNotEmpty()) {
            tv_reviews_label.visibility = View.VISIBLE
            reviewsAdapter?.setData(state.reviews)
            reviewsAdapter?.notifyDataSetChanged()
        }

        if (state.trailers.isNotEmpty()) {
            tv_trailers_label.visibility = View.VISIBLE

            trailersAdapter?.setData(state.trailers)
            trailersAdapter?.notifyDataSetChanged()
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
