package com.dummy.shubhammishra.intern.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dummy.shubhammishra.intern.Classes.Details
import com.dummy.shubhammishra.intern.PersonDetails
import com.dummy.shubhammishra.intern.R
import kotlinx.android.synthetic.main.recyclerview_activity_one.view.*

class ActivityOneAdapter(var listInfo:ArrayList<Details>): RecyclerView.Adapter<ActivityOneAdapter.ActivityHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
        val lf=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ActivityHolder(lf.inflate(R.layout.recyclerview_activity_one,parent,false))
    }

    override fun getItemCount(): Int=listInfo.size

    override fun onBindViewHolder(holder: ActivityHolder, position: Int) {
        holder.itemView.tvDate.text=listInfo[position].date
        holder.itemView.tvStatus.text=listInfo[position].task
        holder.itemView.tvBill.text="BN-"+listInfo[position].bookingId.toString()
        holder.itemView.tvAmount.text="Rs."+listInfo[position].amount.toString()
        holder.itemView.tvType.text=listInfo[position].job_type
        holder.itemView.tvName.text=listInfo[position].name
        holder.itemView.isClickable=true
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,PersonDetails::class.java)
            intent.putExtra("Personal",listInfo[position])
            holder.itemView.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context).load(listInfo[position].photoId).into(holder.itemView.ivProfile)
    }

    class ActivityHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}