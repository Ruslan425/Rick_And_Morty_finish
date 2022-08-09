package ru.romazanov.rickandmortyfinish.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import ru.romazanov.rickandmortyfinish.R
import ru.romazanov.rickandmortyfinish.databinding.CustomFilterButtonBinding

class CustomFiltersButton(context: Context, attrs: AttributeSet) :
    MaterialCardView(context, attrs) {

    private val binding: CustomFilterButtonBinding =
        CustomFilterButtonBinding.inflate(LayoutInflater.from(context), this)

    val image = binding.image


}