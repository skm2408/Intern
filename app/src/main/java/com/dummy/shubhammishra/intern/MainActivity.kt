package com.dummy.shubhammishra.intern

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dummy.shubhammishra.intern.Classes.Details
import com.dummy.shubhammishra.intern.Classes.DetailsArray
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("My Orders")
        progressDialog=ProgressDialog(this)
        progressDialog.create()
        progressDialog.setMessage("Loading Your Order List...")
        progressDialog.show()
        var listDetails = ArrayList<Details>()
        val databaseReference = FirebaseDatabase.getInstance().reference.child("Details")
        /*
        * TO Check if database exist in Firebase or not
        * */
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val details = p0.getValue(DetailsArray::class.java)
                if (details == null) {
                    //To generate random 20 data and upload on Firebase
                    GetDetails(listDetails).execute()
                } else {
                    //if database on Firebase exist then directly fetch from it
                    startEvent()
                }
            }
        })

    }

    private fun startEvent() {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("Details")
        databaseReference.keepSynced(true)
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                progressDialog.dismiss()
                val details = p0.getValue(DetailsArray::class.java)
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("Details", details)
                startActivity(intent)
                finish()
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }


    inner class GetDetails(var listDetails: ArrayList<Details>) : AsyncTask<ArrayList<Details>, Unit, DetailsArray>() {
        override fun onPostExecute(result: DetailsArray) {
            super.onPostExecute(result)
            val databaseReference = FirebaseDatabase.getInstance().reference.child("Details").push()
            databaseReference.setValue(result).addOnSuccessListener {
                startEvent()
            }
        }

        override fun doInBackground(vararg params: ArrayList<Details>): DetailsArray {
            /*
            * Data is generated.Set of 20 data is generated and uploaded on Firebase using random Function
            * */
            val date = arrayOf("Mon,Dec 04,11:45 AM", "Fri,Jan 11,10:35AM", "Tue,Nov 24,5:40PM", "Wed,Feb 6,01:30PM", "Thur,Oct 02,10:00AM", "Sat,June 06,6:30PM")
            val type = arrayOf("Electrician", "Plumber", "Painter", "Carpenter")
            val status = arrayOf("Pending", "Completed", "In Progress", "Cancelled")
            val name = arrayOf("Raman", "Umang", "Ashish", "Atul", "Aman","Keshav")
            val profileId = arrayOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5)
            val r: Random = Random()
            for (i in 0..19) {
                listDetails.add(Details(System.currentTimeMillis().toString(), date.get(r.nextInt(6)), 66000 + r.nextInt(999), type.get(r.nextInt(4)), name.get(r.nextInt(6)), status.get(r.nextInt(4)), r.nextInt(1000).toDouble(), profileId.get(r.nextInt(5))))
            }

            return DetailsArray(listDetails)
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }
}