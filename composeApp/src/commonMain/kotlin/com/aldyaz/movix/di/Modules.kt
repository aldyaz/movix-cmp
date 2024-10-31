package com.aldyaz.movix.di

import com.aldyaz.movix.core.coroutines.CoroutinesContextProvider
import com.aldyaz.movix.data.cloud.MovieCloudDataSource
import com.aldyaz.movix.data.cloud.MovieCloudDataSourceImpl
import com.aldyaz.movix.data.cloud.TvShowCloudDataSource
import com.aldyaz.movix.data.cloud.TvShowCloudDataSourceImpl
import com.aldyaz.movix.data.repository.MovieRepositoryImpl
import com.aldyaz.movix.data.repository.TvShowRepositoryImpl
import com.aldyaz.movix.domain.interactor.GetAiringTodayTvShowsUseCase
import com.aldyaz.movix.domain.interactor.GetMovieDetailUseCase
import com.aldyaz.movix.domain.interactor.GetNowPlayingMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetOnTheAirTvShowsUseCase
import com.aldyaz.movix.domain.interactor.GetPopularMoviesUseCase
import com.aldyaz.movix.domain.interactor.GetTopRatedMoviesUseCase
import com.aldyaz.movix.domain.mapper.HttpExceptionToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieCastToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieCastsToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieListToDomainMapper
import com.aldyaz.movix.domain.mapper.MovieToDomainMapper
import com.aldyaz.movix.domain.mapper.TvShowListToDomainMapper
import com.aldyaz.movix.domain.mapper.TvShowToDomainMapper
import com.aldyaz.movix.domain.repository.MovieRepository
import com.aldyaz.movix.domain.repository.TvShowRepository
import com.aldyaz.movix.navigation.CircuitUiFactory
import com.aldyaz.movix.presentation.mapper.MovieDetailToPresentationMapper
import com.aldyaz.movix.presentation.mapper.MovieItemToPresentationMapper
import com.aldyaz.movix.presentation.mapper.MovieListToPresentationMapper
import com.aldyaz.movix.presentation.viewmodel.MainHomeTabViewModel
import com.aldyaz.movix.presentation.viewmodel.MainViewModel
import com.aldyaz.movix.presentation.viewmodel.MovieDetailViewModel
import com.aldyaz.movix.source.remote.KtorTmdbRemoteService
import com.aldyaz.movix.source.remote.TmdbRemoteService
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val coreModule = module {
    factory {
        CoroutinesContextProvider.Default
    }.bind<CoroutinesContextProvider>()
}

val dataModule = module {
    singleOf(::KtorTmdbRemoteService).bind<TmdbRemoteService>()
    factoryOf(::MovieCloudDataSourceImpl).bind<MovieCloudDataSource>()
    factoryOf(::MovieRepositoryImpl).bind<MovieRepository>()
    factoryOf(::TvShowCloudDataSourceImpl).bind<TvShowCloudDataSource>()
    factoryOf(::TvShowRepositoryImpl).bind<TvShowRepository>()
}

val domainModule = module {
    factoryOf(::HttpExceptionToDomainMapper)
    factoryOf(::MovieListToDomainMapper)
    factoryOf(::MovieToDomainMapper)
    factoryOf(::TvShowListToDomainMapper)
    factoryOf(::TvShowToDomainMapper)
    factoryOf(::MovieCastsToDomainMapper)
    factoryOf(::MovieCastToDomainMapper)

    factoryOf(::GetMovieDetailUseCase)
    factoryOf(::GetNowPlayingMoviesUseCase)
    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::GetTopRatedMoviesUseCase)
    factoryOf(::GetAiringTodayTvShowsUseCase)
    factoryOf(::GetOnTheAirTvShowsUseCase)
}

val presentationModule = module {
    factoryOf(::MovieItemToPresentationMapper)
    factoryOf(::MovieListToPresentationMapper)
    factoryOf(::MovieDetailToPresentationMapper)
    viewModelOf(::MainViewModel)
    viewModelOf(::MainHomeTabViewModel)
    viewModelOf(::MovieDetailViewModel)
}

val uiModule = module {
    factory<Ui.Factory> {
        CircuitUiFactory()
    }
    factory {
        Circuit.Builder()
            .addUiFactory(get())
            .build()
    }
}
