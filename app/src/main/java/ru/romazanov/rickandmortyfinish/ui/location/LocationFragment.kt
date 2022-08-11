package ru.romazanov.rickandmortyfinish.ui.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.romazanov.appComponent
import ru.romazanov.rickandmortyfinish.data.interactors.location.LocationInteractor
import ru.romazanov.rickandmortyfinish.data.models.location.Location
import ru.romazanov.rickandmortyfinish.databinding.FragmentLocationBinding
import javax.inject.Inject


class LocationFragment: MvpAppCompatFragment(), LocationView {

    @Inject
    lateinit var interactor: LocationInteractor

    var adapter: LocationAdapter? = null



    private val presenter: LocationPresenter by moxyPresenter {
        LocationPresenter(interactor)
    }

    private var _binding: FragmentLocationBinding? = null
    val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.injectLocationFragment(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun init() {
        adapter = LocationAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            this.state = BottomSheetBehavior.STATE_HIDDEN
        }
        binding.recyclerView.addOnScrollListener(
            object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.getNextPage()
                    }
                }
            }
        )
    }

    override fun addLocationList(list: List<Location>) {
        adapter?.addToList(list)
    }

    override fun showError() {
        Toast.makeText(context, "Все локации", Toast.LENGTH_SHORT).show()
    }


}