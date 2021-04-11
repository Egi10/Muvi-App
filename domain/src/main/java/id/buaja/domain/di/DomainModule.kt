package id.buaja.domain.di

import id.buaja.domain.usecase.MovieUseCase
import id.buaja.domain.usecase.MovieUseCaseImpl
import org.koin.dsl.module

/**
 * Created by Julsapargi Nursam on 4/11/21.
 */

val domainModule = module {
    factory<MovieUseCase> {
        MovieUseCaseImpl(get())
    }
}