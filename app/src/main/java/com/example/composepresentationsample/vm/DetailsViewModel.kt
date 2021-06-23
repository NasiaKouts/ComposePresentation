package com.example.composepresentationsample.vm

import androidx.lifecycle.ViewModel
import com.example.composepresentationsample.data.FakeContactData
import com.example.composepresentationsample.model.Contact

class DetailsViewModel : ViewModel() {

    // for now, we have static list of immutable contacts
    private val allContacts: List<Contact> = FakeContactData.allContacts

    fun getContactInfo(contactId: Int): Contact = allContacts.find {
        it.id == contactId
    }!!
}