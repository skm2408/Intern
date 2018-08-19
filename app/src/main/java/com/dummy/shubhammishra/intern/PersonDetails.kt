package com.dummy.shubhammishra.intern

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dummy.shubhammishra.intern.Classes.Details
import com.dummy.shubhammishra.intern.Classes.OrderDetails
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        val info=intent.getSerializableExtra("Personal") as Details
        val info2=intent.getSerializableExtra("PersonalDetails") as OrderDetails
        setTitle("BN-"+info.bookingId.toString())
        Glide.with(this).load(info.photoId).into(iv2Profile)
        tv2Date.text=info.date
        tv2Stat.text=info.task
        tv2Type.text=info.job_type
        tvRatePerHour.text=info2.rate
        tvTax.text=info2.tax
        tvDiscount.text=info2.discount.toString()
        tvGrandTotal.text=info.amount.toString()
        val stime=info2.time.substring(0,info2.time.indexOf("-"))
        val etime=info2.time.substring(info2.time.indexOf("-")+1)
        tvStart.text=stime
        tvEnd.text=etime
        tvTotalTime.text=""
        tv2Status.text=info.task
        tvAddress.text=info2.address
        tvPlace.text=info2.place
        if(info.job_type.equals("Electrician"))
        {
            tvRequired.text="Fan,Switches and Light Repair"
        }
        else if(info.job_type.equals("Plumber"))
        {
            tvRequired.text="Basin,Tap Leakage"
        }
        else if(info.job_type.equals("Painter"))
        {
            tvRequired.text="Side Walls,Bedroom Wall Paint"
        }
        else
        {
            tvRequired.text="Drawing Halls' Furniture"
        }
        btnStatus.setText(info2.booking)
    }
}
