package ru.romazanov.rickandmortyfinish.ui.startScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.romazanov.rickandmortyfinish.R
import ru.romazanov.rickandmortyfinish.databinding.FragmentStartBinding

class StartFragment : Fragment() {


    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

       binding.characters.setOnClickListener {
        findNavController().navigate(R.id.action_startFragment_to_characterList)
       }
        return binding.root
    }


}