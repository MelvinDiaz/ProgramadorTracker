package com.diaz.programadortracker.programador.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.diaz.programadortracker.ProgramadorTrackerApplication
import com.diaz.programadortracker.programador.data.Programador
import com.diaz.programadortracker.programador.data.ProgramadorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgramadorViewModel(
    private val repository: ProgramadorRepository

) : ViewModel() {

    private val _programadoresList = MutableStateFlow<List<Programador>>(emptyList())
    val programadoresList: StateFlow<List<Programador>> = _programadoresList
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name
    private val _language = MutableStateFlow("")
    val language: StateFlow<String> = _language
    private val _experiencia = MutableStateFlow(0)
    val experiencia: StateFlow<Int> = _experiencia
    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getProgramadores()
    }


    private fun getProgramadores() {
        _programadoresList.value = repository.getProgramadores()
    }

    fun addProgramador() {
        viewModelScope.launch {
            setLoading(true)
            val programador = Programador(
                id = _id.value,
                nombre = _name.value,
                lenguaje = _language.value,
                experiencia = _experiencia.value
            )
            repository.addProgramador(programador)
            getProgramadores()
            setLoading(false)
        }
        
    }

    fun getProgramador(id: Int): Programador {
        return repository.getProgramadorById(id)
    }

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ProgramadorTrackerApplication
                ProgramadorViewModel(app.programadorRepository)
            }
        }
    }
}