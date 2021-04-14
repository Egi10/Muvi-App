package id.buaja.domain.model

import id.buaja.domain.BuildConfig

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */

data class Details(
    val idMovie: String = "",
    val poster: String = "",
    val title: String = "",
    val duration: String = "",
    val genre: String = "",
    val overview: String = "",
    val cast: List<Cast> = emptyList(),
    val inFavorite: Boolean = false
) {
    val image = "${BuildConfig.BASE_URL_IMAGE}$poster"
}
