package au.com.broscience.nimblesurveys.util

import androidx.test.espresso.idling.CountingIdlingResource


/**
 * Created by Jean Tadebois.
 */
class CountingIdlingResourceWrapper(resourceName: String) {
    val countingIdlingResource: CountingIdlingResource = CountingIdlingResource(resourceName)

    var isProcessing: Boolean = false

    fun increment() {
        if (!isProcessing) {
            countingIdlingResource.increment()
            isProcessing = true
        }
    }

    fun decrement() {
        if (isProcessing) {
            countingIdlingResource.decrement()
            isProcessing = false
        }
    }
}