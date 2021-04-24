package au.com.broscience.nimblesurveys.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import au.com.broscience.nimblesurveys.model.Survey
import au.com.broscience.nimblesurveys.repository.api.ISurveyRepository
import javax.inject.Inject

/**
 * Created by Jean Tadebois.
 */
class SurveyDataSourceFactory @Inject constructor(
    private val surveyRepository: ISurveyRepository
) : DataSource.Factory<Int, Survey>() {

    val surveyDataSourceLiveData = MutableLiveData<SurveyDataSource>()

    override fun create(): DataSource<Int, Survey> {
        val newsDataSource = SurveyDataSource(surveyRepository)
        surveyDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}