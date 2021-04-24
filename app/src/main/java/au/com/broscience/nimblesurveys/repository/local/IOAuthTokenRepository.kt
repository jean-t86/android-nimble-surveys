package au.com.broscience.nimblesurveys.repository.local

import au.com.broscience.nimblesurveys.backend.OAuthToken

/**
 * Created by Jean Tadebois.
 */
interface IOAuthTokenRepository {
    fun getToken(): OAuthToken

    fun saveToken(oAuthToken: OAuthToken)
}