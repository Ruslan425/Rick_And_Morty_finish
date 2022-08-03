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

    private var visible = true

    fun changeIcon() {
        visible = if (visible) {
            binding.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            !visible
        } else {
            binding.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            !visible
        }
    }

}