package br.com.molero.guests.viwmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.molero.guests.service.model.GuestModel
import br.com.molero.guests.service.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val mGuestRepository: GuestRepository = GuestRepository()

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence:Boolean){
        val guest = GuestModel(name,presence)
        mGuestRepository.save(guest)
    }
}