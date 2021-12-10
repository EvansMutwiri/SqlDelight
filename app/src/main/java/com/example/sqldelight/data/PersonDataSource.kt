package com.example.sqldelight.data

import kotlinx.coroutines.flow.Flow
import sqldelight2.persondb.PersonEntity

interface PersonDataSource {
    suspend fun getPersonById(id: Long): PersonEntity?

    fun getAllPersons(): Flow<List<PersonEntity>>

    suspend fun deletePersonById(id: Long)

    suspend fun insertPerson(firstName: String, lastName: String, id: Long? = null)
}