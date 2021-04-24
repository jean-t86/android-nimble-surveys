package au.com.broscience.nimblesurveys.backend.api

import au.com.broscience.nimblesurveys.backend.OAuthToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Jean Tadebois.
 */
interface TokenApi {
    companion object {
        const val GRANT_TYPE = "password"
        const val USERNAME = "carlos@nimbl3.com"
        const val PASSWORD = "antikera"
    }

    @FormUrlEncoded
    @POST("/oauth/token")
    fun postCredentials(
        @Field("grant_type") grantType: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<OAuthToken>
}