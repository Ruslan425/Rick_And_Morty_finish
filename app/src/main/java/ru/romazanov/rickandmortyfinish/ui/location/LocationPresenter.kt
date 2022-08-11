package ru.romazanov.rickandmortyfinish.ui.location

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.romazanov.rickandmortyfinish.data.interactors.location.LocationInteractor
import ru.romazanov.rickandmortyfinish.data.models.location.Location

class LocationPresenter(
    val interactor: LocationInteractor
): MvpPresenter<LocationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        interactor
            .getLocationAnswer(page = "", hashMapOf())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe{ answer ->
                addList(answer.location)
            }
    }

    private fun addList(list: List<Location>) {
        viewState.addLocationList(list)
    }

}