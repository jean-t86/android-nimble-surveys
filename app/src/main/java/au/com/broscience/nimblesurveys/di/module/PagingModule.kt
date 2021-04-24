package au.com.broscience.nimblesurveys.di.module

import androidx.paging.PagedList
import au.com.broscience.nimblesurveys.backend.api.SurveyApi
import au.com.broscience.nimblesurveys.repository.SurveyDataSource
import au.com.broscience.nimblesurveys.repository.SurveyDataSourceFactory
import au.com.broscience.nimblesurveys.repository.api.ISurveyRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Jean Tadebois.
 */
@Module
class PagingModule {
    @Provides
    fun providesSurveyDataSource(surveyRepository: ISurveyRepository): SurveyDataSource =
        SurveyDataSource(surveyRepository)

    @Provides
    fun providesSurveyDataSourceFactory(surveyRepository: ISurveyRepository): SurveyDataSourceFactory =
        SurveyDataSourceFactory(surveyRepository)

    @Provides
    fun providesPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(SurveyApi.PAGE_SIZE)
            .setInitialLoadSizeHint(SurveyApi.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }
}