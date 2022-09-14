package com.example.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CityCardViewBinding
import com.example.weatherapp.model.Forecasts
import com.example.weatherapp.model.Location

class CityAdapter(private val searchCity: (String) -> Unit):
    ListAdapter<Forecasts, CityAdapter.CityViewHolder>(CityDifficult) {

    class CityViewHolder(private val binding: CityCardViewBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(data: Forecasts,
        showDetails: (String) -> Unit){
            binding.txtDay.text = data.day
            binding.txtCloudy.text = data.text
            /*binding.root.setOnClickListener {
                showDetails(data.day)
            }*/
        }
    }

    object CityDifficult: DiffUtil.ItemCallback<Forecasts>(){
        override fun areItemsTheSame(oldItem: Forecasts, newItem: Forecasts): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Forecasts, newItem: Forecasts): Boolean {
            return oldItem.day == newItem.day
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityViewHolder (
     CityCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(currentList[position], searchCity)
    }
}