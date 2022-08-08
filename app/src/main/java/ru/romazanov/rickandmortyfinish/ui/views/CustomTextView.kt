package ru.romazanov.rickandmortyfinish.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.romazanov.rickandmortyfinish.databinding.CustomTextFieldBinding

class CustomTextView(
    context: Context,
    attrs: AttributeSet) :
    FrameLayout(context, attrs)  {

        val binding: CustomTextFieldBinding = CustomTextFieldBinding.inflate(LayoutInflater.from(context), this)

    fun setFields(field: String, value: String) {
        binding.field.text = field
        if (value.isEmpty()) {
            binding.value.text = "Unknown"
        } else {
            binding.value.text = value
        }

    }

}