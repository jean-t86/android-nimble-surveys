package au.com.broscience.nimblesurveys.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import au.com.broscience.nimblesurveys.App
import au.com.broscience.nimblesurveys.model.Survey
import au.com.broscience.nimblesurveys.repository.State
import au.com.broscience.nimblesurveys.repository.SurveyDataSource
import au.com.broscience.nimblesurveys.repository.SurveyDataSourceFactory
import au.com.broscience.nimblesurveys.util.CountingIdlingResourceWrapper

class SurveyListViewModel(application: Application) : AndroidViewModel(application) {
    private val surveyDataSourceFactory: SurveyDataSourceFactory =
        (application as App).appComponent.surveyDataSourceFactory()

    private val pagedConfig: PagedList.Config = (application as App).appComponent.pagedListConfig()

    var loading: ObservableBoolean = ObservableBoolean(false)
    var loadingIdlingResourceWrapper = CountingIdlingResourceWrapper("loading")

    var surveyList: LiveData<PagedList<Survey>> = LivePagedListBuilder<Int, Survey>(
        this.surveyDataSourceFactory,
        pagedConfig
    ).build()

    fun refresh() {
        surveyDataSourceFactory.surveyDataSourceLiveData.value?.invalidate()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<SurveyDataSource,
            State>(surveyDataSourceFactory.surveyDataSourceLiveData, SurveyDataSource::state)

    init {
        getState().observeForever {
            when (it) {
                State.DONE_INITIAL -> {
                    loading.set(false)
                    loadingIdlingResourceWrapper.decrement()
                }
                State.LOADING_INITIAL -> {
                    loading.set(true)
                    loadingIdlingResourceWrapper.increment()
                }
            }
        }
    }
}