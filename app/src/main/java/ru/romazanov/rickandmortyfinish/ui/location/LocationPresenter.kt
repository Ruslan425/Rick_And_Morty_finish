package ru.romazanov.rickandmortyfinish.ui.location

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.romazanov.rickandmortyfinish.data.interactors.location.LocationInteractor
import ru.romazanov.rickandmortyfinish.data.models.location.Location
import ru.romazanov.rickandmortyfinish.data.models.location.LocationAnswer

class LocationPresenter(
    val interactor: LocationInteractor
) : MvpPresenter<LocationView>() {

    private var lastAnswer: LocationAnswer? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        interactor
            .getLocationAnswer(page = "", hashMapOf())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { answer ->
                lastAnswer = answer
                addList(answer.location)
            }
    }


    fun getNextPage() {
        val nextPage =
            lastAnswer?.info?.next?.let { page -> page.substringAfter("=").split("&")[0] }
        if (nextPage != null) {
            interactor
                .getLocationAnswer(nextPage!!, hashMapOf())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { answer ->
                    lastAnswer = answer
                    addList(answer.location)
                }
        } else {
            viewState.showError()
        }
    }


    private fun addList(list: List<Location>) {
        viewState.addLocationList(list)
    }

}