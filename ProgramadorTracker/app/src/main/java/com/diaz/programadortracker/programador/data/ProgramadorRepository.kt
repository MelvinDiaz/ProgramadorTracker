package com.diaz.programadortracker.programador.data

class ProgramadorRepository() {
    fun getProgramadores() = programadores

    fun addProgramador(programador: Programador) {
        programadores.add(programador)
    }

    fun getProgramadorById(programadorId: Int): Programador{
        return programadores.find { it.id == programadorId }!!
    }
}