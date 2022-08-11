package ru.romazanov.rickandmortyfinish.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.romazanov.rickandmortyfinish.data.models.location.Location
import ru.romazanov.rickandmortyfinish.databinding.LocationListItemBinding

class LocationAdapter: RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    inner class ViewHolder(binding: LocationListItemBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: LocationListItemBinding
        init {
            this.binding = binding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LocationListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = locationList[position]

        binding.name.text = item.name
        binding.type.text = item.type
        binding.id.text = item.id.toString()

    }

    private val locationList = mutableListOf<Location>()

    fun addToList(list: List<Location>) {
        locationList += list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
}