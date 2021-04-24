package au.com.broscience.nimblesurveys.di.module

import au.com.broscience.nimblesurveys.di.scope.ActivityScope
import au.com.broscience.nimblesurveys.ui.SurveysActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Jean Tadebois.
 */
@Module
abstract class ActivityBindings {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): SurveysActivity
}