package au.com.broscience.nimblesurveys.backend

import com.google.gson.annotations.SerializedName

/**
 * Created by Jean Tadebois.
 */
data class OAuthToken(
    @SerializedName(ACCESS_TOKEN)
    val accessToken: String,

    @SerializedName(TOKEN_TYPE)
    val tokenType: String
) : IOAuthToken {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val TOKEN_TYPE = "token_type"
    }

    override fun getAuthorization(): String {
        return "$tokenType $accessToken"
    }
}