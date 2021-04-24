package au.com.broscience.nimblesurveys

import android.app.Activity
import android.app.Application
import au.com.broscience.nimblesurveys.di.component.AppComponent
import au.com.broscience.nimblesurveys.di.component.DaggerAppComponent
import au.com.broscience.nimblesurveys.di.component.DaggerSurveyRepositoryComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .app(this)
            .surveyApiComponent(
                DaggerSurveyRepositoryComponent
                    .builder()
                    .context(this)
                    .build()
            )
            .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}