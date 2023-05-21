package com.diaz.programadortracker

import android.app.Application
import com.diaz.programadortracker.programador.data.ProgramadorRepository

class ProgramadorTrackerApplication: Application() {
    val programadorRepository: ProgramadorRepository by lazy {
        ProgramadorRepository()
    }
}