package ru.romazanov.rickandmortyfinish.ui.character.characterItem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.romazanov.rickandmortyfinish.R

class CharacterItemFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterItemFragment()
    }

    private lateinit var viewModel: CharacterItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterItemViewModel::class.java)

    }

}