package com.aldyaz.movix.di

import com.aldyaz.movix.data.cloud.MovieCloudDataSource
import com.aldyaz.movix.data.cloud.MovieCloudDataSourceImpl
import com.aldyaz.movix.data.repository.MovieRepositoryImpl
import com.aldyaz.movix.domain.interactor.GetMovieDetailUseCase
import com.aldyaz.movix.domain.interactor.GetNowPlayingMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetPopularMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetTopRatedMoviesUseCase
import com.aldyaz.movix.domain.mapper.HttpExceptionToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieListToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieToDomainMapper
import com.aldyaz.movix.domain.repository.MovieRepository
import com.aldyaz.movix.presentation.viewmodel.MainMovieTabViewModel
import com.aldyaz.movix.source.remote.KtorTmdbRemoteService
import com.aldyaz.movix.source.remote.TmdbRemoteService
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val dataModule = module {
    singleOf(::KtorTmdbRemoteService).bind<TmdbRemoteService>()
    factoryOf(::MovieCloudDataSourceImpl).bind<MovieCloudDataSource>()
    factoryOf(::MovieRepositoryImpl).bind<MovieRepository>()
}

val domainModule = module {
    factory { HttpExceptionToDomainMapper() }
    factory { MovieToDomainMapper() }
    factoryOf(::MovieListToDomainMapper)
    factoryOf(::GetMovieDetailUseCase)
    factoryOf(::GetNowPlayingMoviesUseCase)
    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::GetTopRatedMoviesUseCase)
}

val presentationModule = module {
    viewModelOf(::MainMovieTabViewModel)
}
