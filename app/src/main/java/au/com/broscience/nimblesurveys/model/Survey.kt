package au.com.broscience.nimblesurveys.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Jean Tadebois.
 */
data class Survey(
    @SerializedName(ID)
    val id: String?,

    @SerializedName(TITLE)
    val title: String?,

    @SerializedName(DESCRIPTION)
    val description: String?,

    @SerializedName(COVER_IMAGE_URL)
    val coverImageUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(coverImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Survey> {
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val COVER_IMAGE_URL = "cover_image_url"
        const val SURVEY_KEY = "survey"
        const val SURVEY_LIST_KEY = "surveyList"

        override fun createFromParcel(parcel: Parcel): Survey {
            return Survey(parcel)
        }

        override fun newArray(size: Int): Array<Survey?> {
            return arrayOfNulls(size)
        }
    }
}