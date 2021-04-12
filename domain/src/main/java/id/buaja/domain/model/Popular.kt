package id.buaja.domain.model

import id.buaja.domain.BuildConfig

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

data class Popular(
    val idMovie: Int?,
    val backdropPath: String? = null,
    val title: String? = null,
    val actor: String? = null,
    val genre: String? = null
) {
    val image = "${BuildConfig.BASE_URL_IMAGE}$backdropPath"
}
