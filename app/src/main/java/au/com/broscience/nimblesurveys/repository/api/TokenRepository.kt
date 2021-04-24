package au.com.broscience.nimblesurveys.repository.api

import au.com.broscience.nimblesurveys.backend.OAuthToken
import au.com.broscience.nimblesurveys.backend.api.TokenApi
import retrofit2.Call

/**
 * Created by Jean Tadebois.
 */
class TokenRepository(private val tokenApi: TokenApi) :
    ITokenRepository {
    override fun postCredentials(grantType: String, username: String, password: String): Call<OAuthToken> {
        return tokenApi.postCredentials(grantType, username, password)
    }
}