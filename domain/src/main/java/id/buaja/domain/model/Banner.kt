package id.buaja.domain.model

import id.buaja.domain.BuildConfig

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

data class Banner(val backdropPath: String? = null) {
    val image = "${BuildConfig.BASE_URL_IMAGE}$backdropPath"
}