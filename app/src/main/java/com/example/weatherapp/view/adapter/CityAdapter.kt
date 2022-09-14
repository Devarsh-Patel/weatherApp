package com.example.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CityCardViewBinding
import com.example.weatherapp.model.Location

class CityAdapter(private val searchCity: (String) -> Unit):
    ListAdapter<Location, CityAdapter.CityViewHolder>(CityDifficult) {

    class CityViewHolder(private val binding: CityCardViewBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(data: Location,
        showDetails: (String) -> Unit){
            binding.txtCityName.text = data.city
            binding.root.setOnClickListener {
                showDetails(data.city)
            }
        }
    }

    object CityDifficult: DiffUtil.ItemCallback<Location>(){
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.city == newItem.city
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityViewHolder (
     CityCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(currentList[position], searchCity)
    }
}