package com.daipi.dzbase.room

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
){
    suspend fun createUser(user: User) = userDao.insertAll(user)

    suspend fun updateUser(user: User) = userDao.updateUsers(user)

    suspend fun removeUser(user: User) = userDao.delete(user)

    suspend fun getUsers() = userDao.getAll()
}