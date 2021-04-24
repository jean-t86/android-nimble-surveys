package au.com.broscience.nimblesurveys.backend

import au.com.broscience.nimblesurveys.backend.api.SurveyApi
import au.com.broscience.nimblesurveys.backend.api.TokenApi
import au.com.broscience.nimblesurveys.repository.api.ITokenRepository
import au.com.broscience.nimblesurveys.repository.local.IOAuthTokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


/**
 * Created by Jean Tadebois.
 */
class SurveyApiAuthenticator @Inject constructor(
    private val tokenRepository: ITokenRepository,
    private val oauthTokenRepository: IOAuthTokenRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = tokenRepository
            .postCredentials(
                TokenApi.GRANT_TYPE,
                TokenApi.USERNAME,
                TokenApi.PASSWORD
            )
            .execute()
            .body()

        return if (token != null) {
            oauthTokenRepository.saveToken(OAuthToken(token.accessToken, token.tokenType))

            response
                .request()
                .newBuilder()
                .header(
                    SurveyApi.AUTHORIZATION,
                    token.getAuthorization()
                ).build()
        } else {
            null
        }
    }
}