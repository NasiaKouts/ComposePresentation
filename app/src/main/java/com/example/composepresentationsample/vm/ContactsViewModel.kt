package com.example.composepresentationsample.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composepresentationsample.data.FakeContactData
import com.example.composepresentationsample.model.Contact

class ContactsViewModel : ViewModel() {

    // for now, we have static list of immutable contacts
    private val allContacts: List<Contact> = FakeContactData.allContacts
    val contactsGroupedByInitial: Map<Char, List<Contact>> = allContacts
        .sortedBy { it.firstName.first() }
        .groupBy { it.firstName.first() }

    private val _favoriteContacts = MutableLiveData(FakeContactData.favouriteContactIds)
    val favoriteContactIds: LiveData<Set<Int>> = _favoriteContacts

    fun toggleFavorite(contactId: Int) {
        val currentFavorites = _favoriteContacts.value?.toMutableSet() ?: mutableSetOf()

        if (contactId in currentFavorites) {
            currentFavorites.remove(contactId)
        } else {
            currentFavorites.add(contactId)
        }

        _favoriteContacts.value = currentFavorites
    }
}