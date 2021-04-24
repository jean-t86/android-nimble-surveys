package au.com.broscience.nimblesurveys.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import au.com.broscience.nimblesurveys.backend.SurveyApiAuthenticator
import au.com.broscience.nimblesurveys.backend.SurveyApiInterceptor
import au.com.broscience.nimblesurveys.backend.api.SurveyApi
import au.com.broscience.nimblesurveys.backend.api.TokenApi
import au.com.broscience.nimblesurveys.repository.api.ISurveyRepository
import au.com.broscience.nimblesurveys.repository.api.ITokenRepository
import au.com.broscience.nimblesurveys.repository.api.SurveyRepository
import au.com.broscience.nimblesurveys.repository.api.TokenRepository
import au.com.broscience.nimblesurveys.repository.local.IOAuthTokenRepository
import au.com.broscience.nimblesurveys.repository.local.OAuthTokenRepository
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Jean Tadebois.
 */
@Module
class SurveyRepositoryModule {
    @Provides
    fun providesPreferenceUtils(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    fun providesSurveyRepository(
        authenticator: Authenticator,
        interceptor: Interceptor
    ): ISurveyRepository {
        val okHttpClientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .authenticator(authenticator)

        return SurveyRepository(
            Retrofit
                .Builder()
                .baseUrl(SurveyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
                .create(SurveyApi::class.java)
        )
    }

    @Provides
    fun providesAuthenticator(
        tokenRepository: ITokenRepository,
        oAuthTokenRepository: IOAuthTokenRepository
    ): Authenticator =
        SurveyApiAuthenticator(tokenRepository, oAuthTokenRepository)

    @Provides
    fun providesInterceptor(oAuthTokenRepository: IOAuthTokenRepository): Interceptor =
        SurveyApiInterceptor(oAuthTokenRepository)

    @Provides
    fun providesTokenRepository(): ITokenRepository {
        return TokenRepository(
            Retrofit
                .Builder()
                .baseUrl(SurveyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenApi::class.java)
        )
    }

    @Provides
    fun providesOAuthTokenRepository(sharedPreferences: SharedPreferences): IOAuthTokenRepository =
        OAuthTokenRepository(sharedPreferences)
}