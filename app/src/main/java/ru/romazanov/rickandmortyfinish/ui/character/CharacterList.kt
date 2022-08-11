package ru.romazanov.rickandmortyfinish.ui.character


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.custom_search_field.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.romazanov.appComponent
import ru.romazanov.rickandmortyfinish.R
import ru.romazanov.rickandmortyfinish.databinding.FragmentCharacterListBinding
import ru.romazanov.rickandmortyfinish.di.ViewModelFactory
import javax.inject.Inject

class CharacterList : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding
        get() = _binding!!

    private var _bottom: BottomSheetBehavior<FrameLayout>? = null
    private val bottom
        get() = _bottom!!

    var query = mutableMapOf<String, String>()

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
        _bottom = BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }
        binding.customFiltersButton.setOnClickListener {
            bottomSheetOpenClose()
        }
        
        binding.searchByRadioGroup.setOnCheckedChangeListener {group, checkedId ->
            when(checkedId) {
                binding.searchGroupType.id ->  {
                    binding.searchView.search_field.hint =
                        "Filter by Type"
                    query.put("type", "")
                }
                binding.searchGroupSpecies.id -> {
                    binding.searchView.search_field.hint =
                        "Filter by Species"
                    query.put("species", "")
                }
            }
        }

        binding.searchView.search_field.doOnTextChanged { text,_, _, _ ->
            when(binding.searchByRadioGroup.checkedRadioButtonId) {
                binding.searchGroupType.id ->  {
                    query.put("type", text.toString())
                }
                binding.searchGroupSpecies.id -> {
                    query.put("species", text.toString())
                }
                binding.searchGroupName.id -> {

                }
            }
            data(query)
        }


        bottomSheetSate()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager
    }


    private fun data(query: Map<String, String>) {
        viewLifecycleOwner.lifecycleScope.launch {
           vm.searchTest(query).collectLatest {
               adapter.submitData(it)
           }
        }
    }

    private fun bottomSheetOpenClose() {
        if (bottom.state == BottomSheetBehavior.STATE_HIDDEN) {
            bottom.state = BottomSheetBehavior.STATE_EXPANDED
        } else if (bottom.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottom.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun bottomSheetSate() {
        bottom.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.customFiltersButton.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                } else if(newState == BottomSheetBehavior.STATE_HIDDEN) {
                    binding.customFiltersButton.image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        } )
    }
}
