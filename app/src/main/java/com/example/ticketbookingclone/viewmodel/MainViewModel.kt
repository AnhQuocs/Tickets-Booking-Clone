package com.example.ticketbookingclone.viewmodel

import androidx.lifecycle.LiveData
import com.example.ticketbookingclone.domain.FlightModel
import com.example.ticketbookingclone.domain.LocationModel
import com.example.ticketbookingclone.respository.MainRepository

class MainViewModel {

    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from: String, to: String):
            LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}