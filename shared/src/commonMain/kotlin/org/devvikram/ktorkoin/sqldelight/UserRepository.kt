package org.devvikram.ktorkoin.sqldelight

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.devvikram.ktorkoin.database.User
import org.devvikram.ktorkoin.shared.ktor_koin_db


class UserRepository(driver: SqlDriver) {
    private val database = ktor_koin_db(driver)
    private val queries = database.userQueries

    // Add suspend functions for database operations
    suspend fun insertUser(name: String, email: String?) {
        withContext(Dispatchers.IO) {
            queries.insertUser(name, email)
        }
    }

    suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            queries.selectAllUsers().executeAsList()
        }
    }

    suspend fun getUserById(id: Long): User? {
        return withContext(Dispatchers.IO) {
            queries.selectUserById(id).executeAsOneOrNull()
        }
    }

    suspend fun insertUsersInTransaction(users: List<Pair<String, String?>>) {
        withContext(Dispatchers.IO) {
            database.transaction {
                users.forEach { (name, email) ->
                    queries.insertUser(name, email)
                }
            }
        }
    }

    suspend fun deleteUser(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteUser(id)
        }
    }
}