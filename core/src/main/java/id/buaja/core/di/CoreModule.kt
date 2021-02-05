package id.buaja.core.di

import androidx.room.Room
import id.buaja.core.data.GamesRepositoryImpl
import id.buaja.core.data.source.local.LocalDataSource
import id.buaja.core.data.source.local.room.GamesDao
import id.buaja.core.data.source.local.room.GamesDatabase
import id.buaja.core.data.source.remote.RemoteDataSource
import id.buaja.core.data.source.remote.network.ApiGamesService
import id.buaja.core.domain.repository.GamesRepository
import id.buaja.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Julsapargi Nursam on 12/17/20.
 */

val databaseModule = module {
    factory {
        get<GamesDatabase>().gamesDao()
    }
    single {
        Room.databaseBuilder(
            get(), GamesDatabase::class.java, "Games.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiGamesService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    single<GamesRepository> {
        GamesRepositoryImpl(get(), get(),get())
    }
    factory { AppExecutors() }
}
