package com.example.sqldelight.data

import com.eazybytes.sqldelight2.PersonDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import sqldelight2.persondb.PersonEntity

class PersonDataSourceImpl ( db: PersonDatabase): PersonDataSource{

    private val queries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): PersonEntity? {
     return withContext(Dispatchers.IO) {
         queries.getPersonById(id).executeAsOneOrNull()
     }
    }

    override fun getAllPersons(): Flow<List<PersonEntity>> {
        return queries.getAllPersons().asFlow().mapToList()
    }

    override suspend fun deletePersonById(id: Long) {
        return withContext(Dispatchers.IO){
            queries.deletePersonById(id)
        }
    }

    override suspend fun insertPerson(firstName: String, lastName: String, id: Long?) {
        return withContext(Dispatchers.IO){
            queries.insertPerson(id, firstName, lastName)
        }
    }

}