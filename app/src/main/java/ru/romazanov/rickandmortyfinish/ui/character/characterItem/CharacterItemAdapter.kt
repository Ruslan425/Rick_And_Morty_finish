package ru.romazanov.rickandmortyfinish.ui.character.characterItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.romazanov.rickandmortyfinish.databinding.CharacterEpisodeItemBinding


class CharacterItemAdapter() : RecyclerView.Adapter<CharacterItemAdapter.CharacterItemViewHolder>(){

    private var dataList: List<String> = listOf()

    fun setDataList(list: List<String>) {
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

    class CharacterItemViewHolder(binding: CharacterEpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: CharacterEpisodeItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CharacterEpisodeItemBinding.inflate(layoutInflater, parent, false)
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val binding = holder.binding
        binding.name.text = "TEST"
        binding.episode.text = "TEST"
        binding.date.text = dataList[position]
    }

}