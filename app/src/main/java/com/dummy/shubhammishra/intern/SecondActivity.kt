package com.dummy.shubhammishra.intern

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dummy.shubhammishra.intern.Adapters.ActivityOneAdapter
import com.dummy.shubhammishra.intern.Classes.DetailsArray
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val listDetails=intent.getSerializableExtra("Details") as DetailsArray
        val adapter=ActivityOneAdapter(listDetails.listDetails)
        setTitle("My Orders")
        mainRecyclerView.layoutManager=LinearLayoutManager(this@SecondActivity)
        mainRecyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
}
