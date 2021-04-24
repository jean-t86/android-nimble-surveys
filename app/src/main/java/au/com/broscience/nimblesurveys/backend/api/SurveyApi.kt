package au.com.broscience.nimblesurveys.backend.api

import au.com.broscience.nimblesurveys.model.Survey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jean Tadebois.
 */
interface SurveyApi {
    companion object {
        const val BASE_URL = "https://nimble-survey-api.herokuapp.com/"
        const val PAGE_SIZE = 10
        const val AUTHORIZATION = "Authorization"
    }

    @GET("/surveys.json")
    fun getSurveys(): Call<List<Survey>>

    @GET("/surveys.json")
    fun getSurveys(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<List<Survey>>
}
