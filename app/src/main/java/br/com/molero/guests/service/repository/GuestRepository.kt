package br.com.molero.guests.service.repository

import br.com.molero.guests.service.model.GuestModel

class GuestRepository {

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }
    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }
    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD - Create, Read, Update,Delete

    fun save(guest: GuestModel) {

    }

    fun update(guest: GuestModel) {

    }
    fun delete(guest: GuestModel) {

    }
}