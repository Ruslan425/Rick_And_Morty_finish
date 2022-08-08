package ru.romazanov.rickandmortyfinish.ui.character


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.romazanov.appComponent
import ru.romazanov.rickandmortyfinish.databinding.FragmentCharacterListBinding
import ru.romazanov.rickandmortyfinish.di.ViewModelFactory
import javax.inject.Inject

class CharacterList : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val vm: CharacterListViewModel by viewModels { viewModelFactory }

    private val adapter: CharacterListAdapter by lazy {
        CharacterListAdapter(findNavController())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.injectCharacterListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        binding.customFiltersButton.setOnClickListener {
            binding.customFiltersButton.changeIcon()
        }

        data()
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy != 0) vm.list(hashMapOf())
                }
            }
        )
    }

    private fun data() {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.listFlow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}


