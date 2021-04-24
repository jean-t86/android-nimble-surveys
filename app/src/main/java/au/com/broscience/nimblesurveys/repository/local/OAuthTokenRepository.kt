package au.com.broscience.nimblesurveys.repository.local

import android.content.SharedPreferences
import au.com.broscience.nimblesurveys.backend.OAuthToken
import javax.inject.Inject

/**
 * Created by Jean Tadebois.
 */
class OAuthTokenRepository @Inject constructor(private val sharedPreferences: SharedPreferences) :
    IOAuthTokenRepository {
    override fun getToken(): OAuthToken {
        val accessToken = sharedPreferences.getString(OAuthToken.ACCESS_TOKEN, "")
        val tokenType = sharedPreferences.getString(OAuthToken.TOKEN_TYPE, "")
        return if (accessToken != null && tokenType != null) {
            OAuthToken(
                accessToken,
                tokenType
            )
        } else {
            OAuthToken("", "")
        }
    }

    override fun saveToken(oAuthToken: OAuthToken) {
        sharedPreferences
            .edit()
            .putString(OAuthToken.ACCESS_TOKEN, oAuthToken.accessToken)
            .putString(OAuthToken.TOKEN_TYPE, oAuthToken.tokenType)
            .apply()
    }
}