package ru.romazanov.rickandmortyfinish.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import ru.romazanov.rickandmortyfinish.databinding.CharacterListItemBinding

class CharacterListAdapter(
    private val navController: NavController
) : PagingDataAdapter<Character, CharacterListAdapter.CharacterListViewHolder>(CHARACTER_COMPARATOR) {

    class CharacterListViewHolder(binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: CharacterListItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CharacterListItemBinding.inflate(layoutInflater, parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position)!!
        val direction = CharacterListDirections.actionCharacterListToCharacterItemFragment(item)
        binding.name.text = item.name
        binding.species.text = item.species
        Glide.with(binding.avatar)
            .load(item.image)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.avatar)
        binding.cardView.setOnClickListener {
            navController.navigate(direction)
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }

}