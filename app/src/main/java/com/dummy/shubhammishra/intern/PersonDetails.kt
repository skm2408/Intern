package com.dummy.shubhammishra.intern

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dummy.shubhammishra.intern.Classes.Details
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        val info=intent.getSerializableExtra("Personal") as Details
        setTitle("BN-"+info.bookingId.toString())
        Glide.with(this).load(info.photoId).into(iv2Profile)
    }
}
