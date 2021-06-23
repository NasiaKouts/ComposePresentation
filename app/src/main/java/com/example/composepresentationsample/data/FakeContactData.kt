package com.example.composepresentationsample.data

import com.example.composepresentationsample.model.Contact

object FakeContactData {
    val contact1 = Contact(
        id = 1,
        firstName = "Sherlock",
        lastName = "Holmes",
        number = randomNumber()
    )

    val contact2 = Contact(
        id = 2,
        firstName = "Mycroft",
        lastName = "Holmes",
        number = randomNumber()
    )

    val contact3 = Contact(
        id = 3,
        firstName = "John",
        lastName = "Watson",
        number = randomNumber()
    )

    val contact4 = Contact(
        id = 4,
        firstName = "Lincoln",
        lastName = "Burrows",
        number = randomNumber()
    )

    val contact5 = Contact(
        id = 5,
        firstName = "Michael",
        lastName = "Scofield",
        number = randomNumber()
    )

    val contact6 = Contact(
        id = 6,
        firstName = "Sara",
        lastName = "Tancredi",
        number = randomNumber()
    )

    val contact7 = Contact(
        id = 7,
        firstName = "Glenn",
        lastName = "Rhee",
        number = randomNumber()
    )

    val contact8 = Contact(
        id = 8,
        firstName = "Daryl",
        lastName = "Dixon",
        number = randomNumber()
    )

    val contact9 = Contact(
        id = 9,
        firstName = "Maggie",
        lastName = "Greene",
        number = randomNumber()
    )

    val contact10 = Contact(
        id = 10,
        firstName = "Rosita",
        lastName = "Espinosa",
        number = randomNumber()
    )

    val contact11 = Contact(
        id = 11,
        firstName = "Elena",
        lastName = "Gilbert",
        number = randomNumber()
    )

    val contact12 = Contact(
        id = 12,
        firstName = "Stefan",
        lastName = "Salvatore",
        number = randomNumber()
    )

    val contact13 = Contact(
        id = 13,
        firstName = "Damon",
        lastName = "Salvatore",
        number = randomNumber()
    )

    val contact14 = Contact(
        id = 14,
        firstName = "Claire",
        lastName = "Littleton",
        number = randomNumber()
    )

    val contact15 = Contact(
        id = 15,
        firstName = "Charlie",
        lastName = "Pace",
        number = randomNumber()
    )

    val contact16 = Contact(
        id = 16,
        firstName = "Jack",
        lastName = "Shephard",
        number = randomNumber()
    )

    val contact17 = Contact(
        id = 17,
        firstName = "Clarke",
        lastName = "Griffin",
        number = randomNumber()
    )

    val contact18 = Contact(
        id = 18,
        firstName = "Octavia",
        lastName = "Blake",
        number = randomNumber()
    )

    val contact19 = Contact(
        id = 19,
        firstName = "Raven",
        lastName = "Reyes",
        number = randomNumber()
    )

    val contact20 = Contact(
        id = 20,
        firstName = "John",
        lastName = "Diggle",
        number = randomNumber()
    )

    val allContacts = listOf(
        contact1,
        contact2,
        contact3,
        contact4,
        contact5,
        contact6,
        contact7,
        contact8,
        contact9,
        contact10,
        contact11,
        contact12,
        contact13,
        contact14,
        contact15,
        contact16,
        contact17,
        contact18,
        contact19,
        contact20
    )

    val favouriteContactIds = setOf(
        contact1.id,
        contact9.id,
        contact13.id,
        contact18.id
    )
}

fun randomNumber(): String = (100000000..999999999).random().toString()