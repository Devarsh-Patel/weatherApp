package com.example.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CityCardViewBinding
import com.example.weatherapp.model.City

class CityAdapter(private val searchCity: (String) -> Unit):
    ListAdapter<City, CityAdapter.CityViewHolder>(CityDifficult) {

    class CityViewHolder(private val binding: CityCardViewBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(data: City,
        showDetails: (String) -> Unit){
            binding.txtCityName.text = data.name
            binding.root.setOnClickListener {
                data.name?.let { city -> showDetails(city) }
            }
        }
    }

    object CityDifficult: DiffUtil.ItemCallback<City>(){
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityViewHolder (
     CityCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(currentList[position], searchCity)
    }
}