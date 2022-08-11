package ru.romazanov.rickandmortyfinish.ui.location

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.romazanov.rickandmortyfinish.data.interactors.location.LocationInteractor
import ru.romazanov.rickandmortyfinish.data.models.location.Location
import ru.romazanov.rickandmortyfinish.data.models.location.LocationAnswer

class LocationPresenter(
    val interactor: LocationInteractor
) : MvpPresenter<LocationView>() {

    private var lastAnswer: LocationAnswer? = null

    val disposable = CompositeDisposable()

    val locationList = mutableListOf<Location>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        interactor
            .getLocationAnswer(page = "", hashMapOf())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { answer ->
                lastAnswer = answer
                locationList += answer.location
                addList(locationList)
            }.addTo(disposable)

    }

    fun getNextPage() {
        disposable.clear()
        val nextPage =
            lastAnswer?.info?.next?.let { page -> page.substringAfter("=").split("&")[0] }
        if (nextPage != null) {
            interactor
                .getLocationAnswer(nextPage!!, hashMapOf())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { answer ->
                    lastAnswer = answer
                    locationList += answer.location
                    addList(locationList)
                }.addTo(disposable)
        } else {
            viewState.showError()
        }
    }

    private fun addList(list: MutableList<Location>) {
        viewState.addLocationList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}