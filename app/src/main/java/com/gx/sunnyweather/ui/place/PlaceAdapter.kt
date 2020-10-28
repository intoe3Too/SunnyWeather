package com.gx.sunnyweather.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gx.sunnyweather.R
import com.gx.sunnyweather.logic.model.Place
import com.gx.sunnyweather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*


class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_place_item, parent, false)

        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = placeList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                with(activity) {
                    drawerLayout.closeDrawers()
                    with(viewModel) {
                        locationLat = place.loc.latitude
                        locationLng = place.loc.longitude
                        placeName = place.name
                    }
                    refreshWeather()
                }
            } else {
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.loc.longitude)
                    putExtra("location_lat", place.loc.latitude)
                    putExtra("place_name", place.name)
                }

                fragment.startActivity(intent)
                activity?.finish()
            }
            fragment.viewModel.savePlace(place)

        }

        return holder
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }
}