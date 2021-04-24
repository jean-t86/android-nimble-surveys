package au.com.broscience.nimblesurveys.backend

import au.com.broscience.nimblesurveys.backend.api.SurveyApi
import au.com.broscience.nimblesurveys.repository.local.IOAuthTokenRepository
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Jean Tadebois.
 */
class SurveyApiInterceptor(private val oauthTokenRepository: IOAuthTokenRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oAuthToken: OAuthToken = oauthTokenRepository.getToken()

        val newRequest = chain
            .request()
            .newBuilder()
            .header(
                SurveyApi.AUTHORIZATION,
                oAuthToken.getAuthorization()
            ).build()
        return chain.proceed(newRequest)
    }
}