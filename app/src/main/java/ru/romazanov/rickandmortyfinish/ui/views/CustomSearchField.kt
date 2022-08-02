package ru.romazanov.rickandmortyfinish.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.card.MaterialCardView
import ru.romazanov.rickandmortyfinish.databinding.CustomSearchFieldBinding

class CustomSearchField(context: Context, attrs: AttributeSet) : MaterialCardView(context, attrs),
    TextView.OnEditorActionListener {

    private val binding: CustomSearchFieldBinding =
        CustomSearchFieldBinding.inflate(LayoutInflater.from(context), this)

    var onTextChangedListener: ((String) -> Unit)? = null

    fun clearText() {
        binding.searchField.setText("")
    }

    init {
        binding.btnCancelText.setOnClickListener {
            clearText()
        }

        binding.searchField.doAfterTextChanged {
            binding.btnCancelText.isVisible = it?.isNotEmpty() == true
            onTextChangedListener?.invoke(it.toString())
        }

        binding.searchField.setOnEditorActionListener(this)
    }

    override fun onEditorAction(view: TextView?, action: Int, event: KeyEvent?): Boolean {
        clearText()
        return true
    }
}