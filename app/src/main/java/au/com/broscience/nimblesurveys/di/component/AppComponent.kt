package au.com.broscience.nimblesurveys.di.component

import android.app.Application
import androidx.paging.PagedList
import au.com.broscience.nimblesurveys.App
import au.com.broscience.nimblesurveys.di.module.ActivityBindings
import au.com.broscience.nimblesurveys.repository.SurveyDataSourceFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by Jean Tadebois.
 */
@Singleton
@Component(modules = [ActivityBindings::class], dependencies = [SurveyRepositoryComponent::class])
interface AppComponent : AndroidInjector<App> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder

        fun surveyApiComponent(surveyRepositoryComponent: SurveyRepositoryComponent): Builder

        fun build(): AppComponent
    }

    fun surveyDataSourceFactory(): SurveyDataSourceFactory

    fun pagedListConfig(): PagedList.Config
}
