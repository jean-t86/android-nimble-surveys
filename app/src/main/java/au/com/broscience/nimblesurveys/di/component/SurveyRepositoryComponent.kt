package au.com.broscience.nimblesurveys.di.component

import android.content.Context
import androidx.paging.PagedList
import au.com.broscience.nimblesurveys.di.module.PagingModule
import au.com.broscience.nimblesurveys.di.module.SurveyRepositoryModule
import au.com.broscience.nimblesurveys.repository.api.ISurveyRepository
import au.com.broscience.nimblesurveys.repository.api.ITokenRepository
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Jean Tadebois.
 */
@Component(modules = [SurveyRepositoryModule::class, PagingModule::class])
interface SurveyRepositoryComponent {
    fun getSurveyRepository(): ISurveyRepository

    fun getTokenRepository(): ITokenRepository

    fun getPagedListConfig(): PagedList.Config

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): SurveyRepositoryComponent
    }
}