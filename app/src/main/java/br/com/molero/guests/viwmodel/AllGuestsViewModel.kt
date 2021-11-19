package br.com.molero.guests.viwmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.molero.guests.service.constants.GuestConstants
import br.com.molero.guests.service.model.GuestModel
import br.com.molero.guests.service.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()

    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){
        if (filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value = mGuestRepository.getAll()
        }else if ((filter == GuestConstants.FILTER.PRESENT)){
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAbsent()
        }
    }
    fun delete(id: Int){
        mGuestRepository.delete(id)
    }
}