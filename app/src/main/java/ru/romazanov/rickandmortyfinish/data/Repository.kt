package ru.romazanov.rickandmortyfinish.data

import ru.romazanov.rickandmortyfinish.data.retorfit.RetrofitApi
import javax.inject.Inject

class Repository @Inject constructor(
    val api: RetrofitApi
) {


}