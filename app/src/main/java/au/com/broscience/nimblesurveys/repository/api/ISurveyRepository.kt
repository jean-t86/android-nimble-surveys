package au.com.broscience.nimblesurveys.repository.api

import au.com.broscience.nimblesurveys.model.Survey
import retrofit2.Call

/**
 * Created by Jean Tadebois.
 */
interface ISurveyRepository {
    fun getSurveys(): Call<List<Survey>>

    fun getSurveys(page: Int, perPage: Int): Call<List<Survey>>
}