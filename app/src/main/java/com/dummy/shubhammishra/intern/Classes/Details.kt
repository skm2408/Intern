package com.dummy.shubhammishra.intern.Classes

import java.io.Serializable

data class Details(var id:String,var date:String,var bookingId:Int,var job_type:String,
                   var name:String,var task:String,var amount:Double,var photoId:Int):Serializable
{
    constructor():this("","",0,"","","",0.0,0)
}