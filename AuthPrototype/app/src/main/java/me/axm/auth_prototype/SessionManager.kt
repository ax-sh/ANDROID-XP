package me.axm.auth_prototype

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
    @Inject
    constructor(
        @ApplicationContext private val appContext: Context,
    ) {
        companion object {
            const val ACCOUNT_TYPE: String = BuildConfig.ACCOUNT_TYPE
        }

        private fun getAnilistAccounts(): Array<out Account> {
            val am = AccountManager.get(appContext)
            val accounts = am.getAccountsByType(ACCOUNT_TYPE)
            val accountCount = accounts.count()
            Timber.tag("getAnilistAccounts")
            Timber.i(accountCount.toString())
            return accounts
        }

        fun saveAuthToken(authToken: String) {
            val am = AccountManager.get(appContext)
            val accountName = "AnilistAccount"
            val account = Account(accountName, ACCOUNT_TYPE)

            if (am.addAccountExplicitly(account, null, null)) {
                Timber.tag("Anilist Adding Account").i(account.toString())
                am.setAuthToken(account, ACCOUNT_TYPE, authToken)
            }
        }

        fun fetchAuthToken(): String? {
            val am = AccountManager.get(appContext)
            val accounts = getAnilistAccounts()

            if (accounts.isNotEmpty()) {
                val account = accounts[0]
                return am.peekAuthToken(account, ACCOUNT_TYPE)
            }
            return null
        }

        fun clearAuthToken(): Boolean {
            val am = AccountManager.get(appContext)
            val accounts = getAnilistAccounts()

            if (accounts.isNotEmpty()) {
                val account = accounts[0]
                val authToken = am.peekAuthToken(account, ACCOUNT_TYPE)

                if (authToken != null) {
                    am.invalidateAuthToken(ACCOUNT_TYPE, authToken)
                    am.removeAccount(account, appContext as Activity, null, null)
                    Timber.tag("Anilist").i("Auth token removed and account deleted")
                    return true
                }
            }
            return false
        }
    }
