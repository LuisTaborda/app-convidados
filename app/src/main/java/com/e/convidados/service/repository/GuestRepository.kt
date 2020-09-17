package com.e.convidados.service.repository

import android.content.Context
import com.e.convidados.service.model.GuestModel

class GuestRepository(context: Context) {

    private val mDatabase = GuestDataBase.getDatabase(context).guestDAO()

    fun getAll(): List<GuestModel> {
        return mDatabase.getInvited()
    }

    fun getPresent(): List<GuestModel> {
        return mDatabase.getPresent()

    }

    fun getAbsent(): List<GuestModel> {
        return mDatabase.getAbsent()
    }


    fun get(id: Int): GuestModel {
        return mDatabase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 1
    }

    fun delete(guest: GuestModel){
        mDatabase.delete(guest)
    }

}