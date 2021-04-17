package com.murali.byjus.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.murali.byjus.R
import com.murali.byjus.servicedata.model.Articles
import com.murali.byjus.ui.view.DetailsActivity
import com.murali.byjus.utils.Common
import com.murali.byjus.utils.Constants


class NewsAdapter(
    private val context: Context,
    val keyName: List<Articles>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext: Context
    private val mKeyNames: List<Articles>

    init {
        this.mContext = context
        this.mKeyNames = keyName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return InfoViewHolder(
            inflater.inflate(
                R.layout.layout_news_adapter,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        //To change body of created functions use File | Settings | File Templates.
        return mKeyNames.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //To change body of created functions use File | Settings | File Templates.

        (holder as InfoViewHolder).bind(
            mKeyNames.get(position),
            context
        )
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }


    class InfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var descriptionTV: TextView
        var authorTV: TextView
        var dateTV: TextView
        var articleImageView: ImageView
        var cContext: Context

        init {
            articleImageView = itemView.findViewById<View>(R.id.media_image) as ImageView
            descriptionTV = itemView.findViewById<View>(R.id.tv_description) as TextView
            authorTV = itemView.findViewById<View>(R.id.tv_author) as TextView
            dateTV = itemView.findViewById<View>(R.id.tv_date) as TextView
            cContext = view.context
        }

        internal fun bind(
            subject: Articles,
            context: Context
        ) {
            descriptionTV.text = subject.description
            authorTV.text = subject.author
            if (subject.publishedAt != null)
                dateTV.text = Common.convertDate(subject.publishedAt)


            Glide.with(context)
                .load(subject.urlToImage)
                .placeholder(R.drawable.ic_placeholder) //placeholder
                .error(R.drawable.ic_placeholder) //error
                .into(articleImageView);

            articleImageView.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("Details", subject)
                context.startActivity(intent)
            })

        }


    }


}