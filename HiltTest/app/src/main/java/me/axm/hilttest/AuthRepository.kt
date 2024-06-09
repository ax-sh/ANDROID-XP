package me.axm.hilttest

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepository
    @Inject
    constructor(
        @ApplicationContext private val appContext: Context,
    ) {
        private val ACCOUNT_TYPE = "ax-sh.anilist.auth"

        private fun getAccounts(): Array<out Account> {
            val accountManager = AccountManager.get(appContext)
            val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
            return accounts
        }

        fun getToken(): String? {
            val accounts = getAccounts()
            val accountCount = accounts.count()
            Log.i("Anilist accountCount", accountCount.toString())
            if (accounts.isNotEmpty()) {
                val account = accounts[0]
                return AccountManager.get(appContext).peekAuthToken(account, ACCOUNT_TYPE)
            }
            return null
        }
    }
