package id.buaja.domain.model

import id.buaja.domain.BuildConfig

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

data class ComingSoon(
    val idMovie: String,
    val backdropPath: String? = null
) {
    val image = "${BuildConfig.BASE_URL_IMAGE}$backdropPath"
}