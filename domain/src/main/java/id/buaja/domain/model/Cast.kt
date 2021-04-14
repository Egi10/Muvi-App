package id.buaja.domain.model

import id.buaja.domain.BuildConfig

/**
 * Created by Julsapargi Nursam on 4/14/21.
 */

data class Cast(
    val name: String,
    val image: String
) {
    val images = "${BuildConfig.BASE_URL_IMAGE}$image"
}
