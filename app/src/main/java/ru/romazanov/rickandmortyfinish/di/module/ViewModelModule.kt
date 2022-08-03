package ru.romazanov.rickandmortyfinish.di.module


import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.interactors.CharacterInteractor
import ru.romazanov.rickandmortyfinish.data.retorfit.RetrofitApi
import ru.romazanov.rickandmortyfinish.di.ViewModelFactory
import ru.romazanov.rickandmortyfinish.di.ViewModelKey
import ru.romazanov.rickandmortyfinish.ui.character.CharacterListViewModel
import javax.inject.Provider
import javax.inject.Singleton


@Module
class ViewModelModule {

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
    @Singleton
    fun provideCharacterInretactor(
        repository: Repository
    ): CharacterInteractor {
        return CharacterInteractor(repository)
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: RetrofitApi
    ): Repository {
        return Repository(api)
    }

    @Provides
    @Singleton
    fun provideApi() : RetrofitApi {
        return Retrofit.Builder()
            .baseUrl("fdsfsd")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }


}