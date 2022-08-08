package ru.romazanov.rickandmortyfinish.ui.character.characterItem


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import ru.romazanov.rickandmortyfinish.databinding.FragmentCharacterItemBinding


class CharacterItemFragment : Fragment() {


    private var _binding: FragmentCharacterItemBinding? = null
    private val binding
        get() = _binding!!

    private val item: CharacterItemFragmentArgs by navArgs()

    private val adapter: CharacterItemAdapter by lazy {
        CharacterItemAdapter()
    }

    private lateinit var viewModel: CharacterItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterItemBinding.inflate(inflater, container, false)

        binding.name.text = item.item.name
        binding.gender.setFields("Gender", item.item.gender)
        binding.status.setFields("Status", item.item.status)
        binding.specie.setFields("Specie", item.item.species)
        binding.origin.setFields("Origin", item.item.origin.name)
        binding.type.setFields("Type", item.item.type)
        binding.location.setFields("Location", item.item.location.name)

        adapter.setDataList(item.item.episode)
        Glide.with(binding.avatar)
            .load(item.item.image)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding.avatar)
        initRecyclerView()

        binding.button.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = true
    }


}