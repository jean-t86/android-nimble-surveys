package au.com.broscience.nimblesurveys.repository.api

import au.com.broscience.nimblesurveys.backend.api.SurveyApi
import au.com.broscience.nimblesurveys.model.Survey
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Jean Tadebois.
 */
class SurveyRepository @Inject constructor(private val surveyApi: SurveyApi) :
    ISurveyRepository {
    override fun getSurveys(): Call<List<Survey>> {
        return surveyApi.getSurveys()
    }

    override fun getSurveys(page: Int, perPage: Int): Call<List<Survey>> {
        return surveyApi.getSurveys(page, perPage)
    }
}