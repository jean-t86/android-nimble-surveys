package au.com.broscience.nimblesurveys.backend

/**
 * Created by Jean Tadebois.
 *
 * Interface used to hold authorization for a REST api
 */
interface IOAuthToken {
    fun getAuthorization(): String
}