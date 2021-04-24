package au.com.broscience.nimblesurveys.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import au.com.broscience.nimblesurveys.R
import au.com.broscience.nimblesurveys.model.Survey
import au.com.broscience.nimblesurveys.util.OnTakeSurveyClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.survey_list_item.view.*
import timber.log.Timber

/**
 * Created by Jean Tadebois.
 */
class SurveyListAdapter(
    private var onTakeSurveyClickListener: OnTakeSurveyClickListener
) : PagedListAdapter<Survey, RecyclerView.ViewHolder>(SurveyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SurveyViewHolder.create(parent)
    }

    companion object {
        val SurveyDiffCallback = object : DiffUtil.ItemCallback<Survey>() {
            override fun areItemsTheSame(oldItem: Survey, newItem: Survey): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Survey, newItem: Survey): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SurveyViewHolder).bind(getItem(position), onTakeSurveyClickListener)
    }

    class SurveyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(survey: Survey?, onTakeSurveyClickListener: OnTakeSurveyClickListener) {
            if (survey != null) {
                itemView.title.text = survey.title
                itemView.description.text = survey.description
                try {
                    Picasso.get().load(survey.coverImageUrl + "l").into(itemView.cover_image)
                } catch (e: Exception) {
                    Timber.e(e)
                }
                itemView.take_survey.setOnClickListener {
                    onTakeSurveyClickListener.onTakeSurveyClick(survey)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): SurveyViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.survey_list_item, parent, false)
                return SurveyViewHolder(view)
            }
        }
    }
}