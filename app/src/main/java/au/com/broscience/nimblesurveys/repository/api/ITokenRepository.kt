package au.com.broscience.nimblesurveys.repository.api

import au.com.broscience.nimblesurveys.backend.OAuthToken
import retrofit2.Call

/**
 * Created by Jean Tadebois.
 */
interface ITokenRepository {
    fun postCredentials(grantType: String, username: String, password: String): Call<OAuthToken>
}