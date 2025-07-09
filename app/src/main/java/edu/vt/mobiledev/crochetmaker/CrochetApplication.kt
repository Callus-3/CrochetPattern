package edu.vt.mobiledev.crochetmaker

import android.app.Application

class CrochetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        StitchRepository.initialize(this)
        PatternRepository.initialize(this)
    }
}