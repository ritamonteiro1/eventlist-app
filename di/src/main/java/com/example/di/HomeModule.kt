package com.example.di

import com.example.core.utils.UseCase
import com.example.featurehome.data.remote.datasource.EventRemoteDataSource
import com.example.featurehome.data.remote.datasource.EventRemoteDataSourceImpl
import com.example.featurehome.data.remote.service.EventService
import com.example.featurehome.data.remote.utils.Constants
import com.example.featurehome.data.repository.EventRepositoryImpl
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository
import com.example.featurehome.domain.usecase.DoCheckInUseCase
import com.example.featurehome.domain.usecase.GetEventDetailsUseCase
import com.example.featurehome.domain.usecase.GetEventListUseCase
import com.example.featurehome.presentation.eventdetails.EventDetailsViewModel
import com.example.featurehome.presentation.eventlist.EventListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeModule = module {
    single<EventService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventService::class.java)
    }

    single<EventRemoteDataSource> {
        EventRemoteDataSourceImpl(get())
    }

    single<EventRepository> {
        EventRepositoryImpl(get(), get())
    }

    single<UseCase<DoCheckInUseCase.Params, Unit>> {
        DoCheckInUseCase(get())
    }

    single<UseCase<GetEventListUseCase.Params, List<Event>>> {
        GetEventListUseCase(get())
    }

    single<UseCase<GetEventDetailsUseCase.Params, EventDetails>> {
        GetEventDetailsUseCase(get())
    }

    viewModel { EventListViewModel(get()) }

    viewModel { EventDetailsViewModel(get(), get()) }
}