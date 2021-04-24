package au.com.broscience.nimblesurveys.util

import au.com.broscience.nimblesurveys.model.Survey

/**
 * Created by Jean Tadebois.
 */
interface OnTakeSurveyClickListener {
    fun onTakeSurveyClick(survey: Survey)
}