package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
        val id: String?,
        val author: String?,
        val content: String?,
        val url: String?)