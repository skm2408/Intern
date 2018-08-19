package com.dummy.shubhammishra.intern.Classes

import java.io.Serializable

data class DetailsArray(var listDetails: ArrayList<Details>):Serializable {
    constructor():this(ArrayList())
}