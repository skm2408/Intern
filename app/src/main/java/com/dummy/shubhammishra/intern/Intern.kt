package com.dummy.shubhammishra.intern

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class Intern: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}