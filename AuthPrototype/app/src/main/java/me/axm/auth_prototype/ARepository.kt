package me.axm.auth_prototype

import me.axm.auth_prototype.auth.AuthResult

interface ARepository {
    suspend fun authenticate(): AuthResult<Unit>
}