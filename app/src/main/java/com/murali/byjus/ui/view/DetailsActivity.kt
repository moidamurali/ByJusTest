package com.murali.byjus.ui.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.murali.byjus.R
import com.murali.byjus.servicedata.model.Articles
import com.murali.byjus.utils.Common
import kotlinx.android.synthetic.main.layout_details.*
import kotlinx.android.synthetic.main.toolbar.*


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.layout_details)

        setUIComponents()
    }

    private fun setBackgroundImage(
        article: Articles,
        background: ConstraintLayout
    ) {
        Glide.with(this).load(article.urlToImage)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    background.setBackground(resource)
                }
            })
    }

    private fun setUIComponents() {
        val data = intent.extras
        val details = data?.getParcelable<Articles>("Details")
        val id = details?.author.toString()
        val background = findViewById<ConstraintLayout>(R.id.root_view)
        main_toolbar.setBackgroundColor(resources.getColor(R.color.transperant_colour))
        back_icon.visibility = View.VISIBLE
        header_title.visibility = View.GONE

        tv_title.setText(details?.title)
        tv_description.setText(details?.description)
        tv_author.setText(details?.author)
        tv_date.setText(details?.publishedAt?.let { Common.convertDate(it) })
        back_icon.setOnClickListener(View.OnClickListener {
            finish()
        })

        details?.let { setBackgroundImage(it, background) }
    }


}