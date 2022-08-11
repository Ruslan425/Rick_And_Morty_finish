package ru.romazanov.rickandmortyfinish.ui.location

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.romazanov.rickandmortyfinish.data.models.location.Location


@StateStrategyType(AddToEndSingleStrategy::class)
interface LocationView: MvpView {
    fun init()
    fun addLocationList(list: List<Location>)
}