package au.com.broscience.nimblesurveys.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import au.com.broscience.nimblesurveys.model.Survey
import au.com.broscience.nimblesurveys.repository.api.ISurveyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

/**
 * Created by Jean Tadebois.
 */
class SurveyDataSource(
    private val surveyRepository: ISurveyRepository
) : PageKeyedDataSource<Int, Survey>() {
    companion object {
        const val FIRST_PAGE = 1
        const val SECOND_PAGE = 2
    }

    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Survey>) {
        updateState(State.LOADING_INITIAL)
        surveyRepository.getSurveys(FIRST_PAGE, params.requestedLoadSize)
            .enqueue(object : Callback<List<Survey>> {
                override fun onResponse(call: Call<List<Survey>>, response: Response<List<Survey>>) {
                    if (response.code() != HttpsURLConnection.HTTP_OK) {
                        updateState(State.ERROR)
                    } else {
                        callback.onResult(
                            response.body()!!,
                            null,
                            SECOND_PAGE
                        )
                        updateState(State.DONE_INITIAL)
                    }
                }

                override fun onFailure(call: Call<List<Survey>>, t: Throwable) {
                    updateState(State.ERROR)
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Survey>) {
        updateState(State.LOADING)
        surveyRepository.getSurveys(params.key, params.requestedLoadSize)
            .enqueue(object : Callback<List<Survey>> {
                override fun onResponse(call: Call<List<Survey>>, response: Response<List<Survey>>) {
                    if (response.code() != HttpsURLConnection.HTTP_OK) {
                        updateState(State.ERROR)
                    } else {
                        callback.onResult(
                            response.body()!!,
                            params.key + 1
                        )
                        updateState(State.DONE)
                    }
                }

                override fun onFailure(call: Call<List<Survey>>, t: Throwable) {
                    updateState(State.ERROR)
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Survey>) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}