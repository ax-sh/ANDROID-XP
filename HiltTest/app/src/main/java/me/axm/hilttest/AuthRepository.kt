package me.axm.hilttest



interface AuthRepository {
    suspend fun doNetworkCall()
}