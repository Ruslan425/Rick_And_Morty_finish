package ru.romazanov.rickandmortyfinish.ui.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.romazanov.rickandmortyfinish.data.models.character.Character
import ru.romazanov.rickandmortyfinish.databinding.CharacterListItemBinding

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private var dataList: List<Character> = listOf()

    fun setDataList(list: List<Character>) {
        dataList = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return if(dataList.isEmpty()) {
            0
        } else {
            dataList.size
        }
    }

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
        binding.name.text = dataList[position].name
        binding.species.text = dataList[position].species
        Glide.with(binding.avatar)
            .load(dataList[position].image)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.avatar)
    }


}