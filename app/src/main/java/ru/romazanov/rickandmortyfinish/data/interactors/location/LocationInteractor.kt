package ru.romazanov.rickandmortyfinish.data.interactors.location


import io.reactivex.rxjava3.core.Observable
import ru.romazanov.rickandmortyfinish.data.Repository
import ru.romazanov.rickandmortyfinish.data.models.location.LocationAnswer
import javax.inject.Inject

class LocationInteractor @Inject constructor(
    val repository: Repository
) {

    fun getLocationAnswer(page: String, query: Map<String, String>): Observable<LocationAnswer> {
        return repository.getLocation(page, query)
    }

}