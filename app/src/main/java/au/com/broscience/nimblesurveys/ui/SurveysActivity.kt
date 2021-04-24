package au.com.broscience.nimblesurveys.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import au.com.broscience.nimblesurveys.R
import au.com.broscience.nimblesurveys.databinding.ActivitySurveysBinding
import au.com.broscience.nimblesurveys.model.Survey
import au.com.broscience.nimblesurveys.repository.State
import au.com.broscience.nimblesurveys.util.CountingIdlingResourceWrapper
import au.com.broscience.nimblesurveys.util.OnTakeSurveyClickListener
import au.com.broscience.nimblesurveys.viewmodel.SurveyListViewModel
import kotlinx.android.synthetic.main.activity_surveys.*


class SurveysActivity : AppCompatActivity(), OnTakeSurveyClickListener {

    lateinit var loadingIdlingResourceWrapper: CountingIdlingResourceWrapper

    private lateinit var viewDataBinding: ActivitySurveysBinding
    private lateinit var viewModel: SurveyListViewModel
    private lateinit var surveyListAdapter: SurveyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_surveys)
        viewModel = ViewModelProvider(this).get(SurveyListViewModel::class.java)
        viewDataBinding.viewmodel = viewModel
        loadingIdlingResourceWrapper = viewModel.loadingIdlingResourceWrapper

        initAdapter()
        initState()
        setupActionBar()
        forceRTLIfSupported()
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        surveyListAdapter = SurveyListAdapter(this)
        recyclerView.adapter = surveyListAdapter
        viewModel.surveyList.observe(this, Observer {
            surveyListAdapter.submitList(it)
        })

        PagerSnapHelper().attachToRecyclerView(recyclerView)
        recyclerViewIndicator.setRecyclerView(recyclerView)
    }

    private fun initState() {
        viewModel.getState().observe(this, Observer { state ->
            when (state) {
                State.DONE_INITIAL -> recyclerViewIndicator.forceUpdateItemCount()
                State.DONE -> recyclerViewIndicator.forceUpdateItemCount()
                State.ERROR -> Toast
                    .makeText(
                        applicationContext,
                        getString(R.string.network_error),
                        Toast.LENGTH_LONG
                    ).show()
            }
        })
    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun forceRTLIfSupported() {
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTakeSurveyClick(survey: Survey) {
        startActivity(
            Intent(this, QuestionsActivity::class.java)
                .putExtra(Survey.SURVEY_KEY, survey)
        )
    }
}