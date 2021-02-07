package com.challenge.turoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.challenge.turoapp.R
import com.challenge.turoapp.repository.remote.model.Business
import com.squareup.picasso.Picasso

class BusinessAdapter :
    RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    private var businessList: List<Business> = emptyList()

    fun setData(businessList: List<Business>) {
        this.businessList = businessList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder =
        BusinessViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )

    override fun getItemCount(): Int = if (businessList.isNullOrEmpty()) 0 else businessList.size

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val business = businessList[position]

        business.apply {
            this.imageUrl?.let {
                Picasso.get().load(it).into(holder.businessImage)
            }

            holder.businessName.text = this.name
            holder.businessReviewCount.text = String.format("(%s)", this.reviewCount.toString())
            holder.businessPhone.text = String.format("Phone: , %s", this.phone)

            this.location?.let {
                holder.businessLocation.text = String.format(
                    "Address: %s, %s,\n%s, %s",
                    it.address1,
                    it.city,
                    it.state,
                    it.zipCode
                )
            }

            holder.businessRatingBar.rating = this.rating
        }
    }

    class BusinessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val businessImage = view.findViewById<ImageView>(R.id.business_image)
        val businessName = view.findViewById<TextView>(R.id.business_name)
        val businessLocation = view.findViewById<TextView>(R.id.business_location)
        val businessRatingBar = view.findViewById<RatingBar>(R.id.business_rating)
        val businessReviewCount = view.findViewById<TextView>(R.id.review_count)
        val businessPhone = view.findViewById<TextView>(R.id.business_phone)
    }
}