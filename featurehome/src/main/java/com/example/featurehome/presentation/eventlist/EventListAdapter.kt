package com.example.featurehome.presentation.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.utils.downloadImage
import com.example.core.utils.toStringDate
import com.example.featurehome.R
import com.example.featurehome.domain.model.Event

class EventListAdapter(
    private val events: List<Event>,
    private val onItemClickListener: (Event) -> Unit
) : RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventListViewHolder {
        return EventListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(events[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class EventListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView =
            itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView =
            itemView.findViewById(R.id.titleTextView)
        private val dateTextView: TextView =
            itemView.findViewById(R.id.dateTextView)


        fun bind(
            event: Event,
            onItemClickListener: (Event) -> Unit
        ) {
            imageView.downloadImage(event.image)
            titleTextView.text = event.title
            dateTextView.text = event.date.toStringDate()
            itemView.setOnClickListener { onItemClickListener.invoke(event) }
        }
    }
}