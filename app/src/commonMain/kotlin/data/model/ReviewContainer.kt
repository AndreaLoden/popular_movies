package data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewContainer(
        val results: List<Review>
) {
}