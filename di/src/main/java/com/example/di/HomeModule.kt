package com.example.di

import com.example.featurehome.data.remote.datasource.EventRemoteDataSource
import com.example.featurehome.data.remote.datasource.EventRemoteDataSourceImpl
import com.example.featurehome.data.remote.service.EventService
import com.example.featurehome.data.remote.utils.Constants
import com.example.featurehome.data.repository.EventRepositoryImpl
import com.example.featurehome.domain.repository.EventRepository
import com.example.featurehome.domain.usecase.DoCheckInUseCase
import com.example.featurehome.domain.usecase.DoCheckInUseCaseImpl
import com.example.featurehome.domain.usecase.GetEventDetailsUseCase
import com.example.featurehome.domain.usecase.GetEventDetailsUseCaseImpl
import com.example.featurehome.domain.usecase.GetEventListUseCase
import com.example.featurehome.domain.usecase.GetEventListUseCaseImpl
import com.example.featurehome.presentation.eventdetails.EventDetailsViewModel
import com.example.featurehome.presentation.eventlist.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeModule = module {
    single<EventService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventService::class.java)
    }

    single<EventRemoteDataSource> {
        EventRemoteDataSourceImpl(get())
    }

    single<EventRepository> {
        EventRepositoryImpl(get(), get(), get())
    }

    single<DoCheckInUseCase> {
        DoCheckInUseCaseImpl(get())
    }

    single<GetEventListUseCase> {
        GetEventListUseCaseImpl(get())
    }

    single<GetEventDetailsUseCase> {
        GetEventDetailsUseCaseImpl(get())
    }

    viewModel { EventListViewModel(get()) }

    viewModel { EventDetailsViewModel(get(), get()) }
}