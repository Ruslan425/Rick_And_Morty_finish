package ru.romazanov.rickandmortyfinish.di.module


import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.interactors.character.CharacterInteractor
import ru.romazanov.rickandmortyfinish.data.retorfit.RetrofitApi
import ru.romazanov.rickandmortyfinish.data.room.Database
import ru.romazanov.rickandmortyfinish.di.ViewModelFactory
import ru.romazanov.rickandmortyfinish.di.ViewModelKey
import ru.romazanov.rickandmortyfinish.ui.character.CharacterListViewModel
import javax.inject.Provider
import javax.inject.Singleton


@Module
class AppModule {

    private val baseUrl = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideViewModelFactory(viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(viewModels)
    }

    @Provides
    @IntoMap
    @ViewModelKey(value = CharacterListViewModel::class)
    fun provideCharacterListViewModel(interactor: CharacterInteractor): ViewModel {
        return CharacterListViewModel(interactor)
    }

    @Provides
    fun provideCharacterInretactor(
        repository: Repository,
        database: Database
    ): CharacterInteractor {
        return CharacterInteractor(repository, database)
    }

    @Provides
    fun provideApi(): RetrofitApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

    @Provides
    fun provideRepository(
        api: RetrofitApi,
        database: Database
    ): Repository {
        return Repository(api, database)
    }
}