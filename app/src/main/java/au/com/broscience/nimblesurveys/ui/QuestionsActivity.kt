package au.com.broscience.nimblesurveys.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.com.broscience.nimblesurveys.R
import au.com.broscience.nimblesurveys.model.Survey
import timber.log.Timber

class QuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val survey = intent.getParcelableExtra<Survey>(Survey.SURVEY_KEY)
        Timber.d("")
    }
}
