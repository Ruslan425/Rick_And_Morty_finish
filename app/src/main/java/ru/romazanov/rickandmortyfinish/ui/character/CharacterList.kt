package ru.romazanov.rickandmortyfinish.ui.character


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.romazanov.appComponent
import ru.romazanov.rickandmortyfinish.databinding.FragmentCharacterListBinding
import ru.romazanov.rickandmortyfinish.di.ViewModelFactory
import ru.romazanov.rickandmortyfinish.ui.character.characterItem.CharacterItemViewModel
import javax.inject.Inject

class CharacterList : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vw: CharacterListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationContext.appComponent.injectCharacterListFragment(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        binding.customFiltersButton.setOnClickListener {
           binding.customFiltersButton.changeIcon()
            Toast.makeText(context, vw.test(), Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }


}