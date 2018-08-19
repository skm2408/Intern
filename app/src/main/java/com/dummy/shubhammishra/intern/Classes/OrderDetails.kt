package com.dummy.shubhammishra.intern.Classes

import java.io.Serializable

data class OrderDetails(var id:String,var address:String,var rate:String,var tax:String,var discount:Double,var time:String,var place:String,var booking:String):Serializable {
    constructor():this("","","","",0.0,"","","")
}
data class OrderDetailsArray(var listOrderDetails: ArrayList<OrderDetails>)
{
    constructor():this(ArrayList())
}