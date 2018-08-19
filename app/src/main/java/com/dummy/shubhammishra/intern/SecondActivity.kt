package com.dummy.shubhammishra.intern

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dummy.shubhammishra.intern.Adapters.ActivityOneAdapter
import com.dummy.shubhammishra.intern.Classes.Details
import com.dummy.shubhammishra.intern.Classes.DetailsArray
import com.dummy.shubhammishra.intern.Classes.OrderDetails
import com.dummy.shubhammishra.intern.Classes.OrderDetailsArray
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {
    var orderDetailsArray:OrderDetailsArray?=null
    lateinit var listDetails:DetailsArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        listDetails = intent.getSerializableExtra("Details") as DetailsArray
        setTitle("My Orders")
        frameDetails.visibility = View.VISIBLE
        //To check if order details database is available on Firebase or not.
        val databaseReference=FirebaseDatabase.getInstance().reference.child("OrderDetails")
        databaseReference.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val data=p0.getValue(OrderDetailsArray::class.java)
                if(data==null)
                {
                    //Database is created as it doesn't exist
                    GetOrderDetails(listDetails.listDetails).execute()
                }
                else
                {
                    //If database exist,it is directly fetched and stored in an array
                    startDetails(orderDetailsArray)
                }
            }
        })
    }

    inner class GetOrderDetails(var listDetails: ArrayList<Details>) : AsyncTask<ArrayList<Details>, Unit, OrderDetailsArray>() {
        override fun onPostExecute(result: OrderDetailsArray?) {
            super.onPostExecute(result)
            val databaseReference=FirebaseDatabase.getInstance().reference.child("OrderDetails").push()
            databaseReference.setValue(result).addOnSuccessListener {
                startDetails(result)
            }
        }

        override fun doInBackground(vararg params: ArrayList<Details>?): OrderDetailsArray? {
            /*
            * Order details are generated using random function and uploaded on firebase
            *
            * */
            val rperhr = arrayOf("Rs 200-Rs 500 per hour", "Rs 100-Rs 300 per hour", "Rs 120-Rs 150 per hour", "Rs 80-Rs 140 per hour", "Rs 140-Rs 180 per hour")
            val startend= arrayOf("4:30PM-5:20PM","10:00AM-10:30AM","11:40AM-12:50PM","2:00PM-3:40PM","9:50AM-10:40AM")
            val place= arrayOf("OFFICE","HOME","HOTEL","HOSPITAL")
            val bookingStatus= arrayOf("Cancel Booking","Re-Book","Book Now")
            val address=arrayOf("c/o Yashwant S.Prabhu , 318, C - Wing, Suyog Co.Housing Society Ltd, T. P.S. Road & III Link Road, Vazira, Borivali, West Mumbai, Maharashtra, 400092",
                    "White C/403, Aamrpali Appt, opp. GHB complex, Ankur Road, Ahmedabad, Gujarat, 380013","13/9, Daksha Bldg, Vallabh Baug Lane, Ghatkopar, Mumbai, Maharashtra, 400077",
                    "13/9, Daksha Bldg, Vallabh Baug Lane, Ghatkopar, Mumbai, Maharashtra, 400077","NO88, Srinivasa Nagar, 2NS Main Road, Kolathur, Chennai, Tamil Nadu 600099",
                    "Life Style International Pvt. Ltd., Near Payal Cinema Complex, Gurgaon, Haryana, 122001")
            val r:Random= Random()
            var listOrderDetails=ArrayList<OrderDetails>()
            for(i in 0..19)
            {
                listOrderDetails.add(OrderDetails(listDetails[i].toString(),address.get(r.nextInt(6)),rperhr.get(r.nextInt(5)),r.nextInt(6).toString()+"%",listDetails[i].amount,startend.get(r.nextInt(5)),place.get(r.nextInt(4)),bookingStatus.get(r.nextInt(3))))
            }
            return OrderDetailsArray(listOrderDetails)
        }
    }

    private fun startDetails(orderDetail: OrderDetailsArray?) {
        val databaseReference=FirebaseDatabase.getInstance().reference.child("OrderDetails")
        databaseReference.addChildEventListener(object:ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
              val order=p0.getValue(OrderDetailsArray::class.java)
                orderDetailsArray=order
                frameDetails.visibility=View.GONE
                setData(orderDetailsArray)
            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

    private fun setData(orderDetailsArray: OrderDetailsArray?) {
        val adapter = ActivityOneAdapter(listDetails.listDetails, orderDetailsArray!!)
        mainRecyclerView.layoutManager = LinearLayoutManager(this@SecondActivity)
        mainRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
