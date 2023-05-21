package com.diaz.programadortracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diaz.programadortracker.programador.ui.ProgramadorDetails
import com.diaz.programadortracker.programador.ui.ProgramadorViewModel

@Composable
fun NavigationHost(
    viewModel: ProgramadorViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ProgramadorViewModel.Factory
    )
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "programadores_screen") {
        composable("programadores_screen") {
            com.diaz.programadortracker.programador.ui.ProgramadoresScreen(viewModel = viewModel)
        }
        composable("add_programador_screen") {
            com.diaz.programadortracker.programador.ui.AddProgramadorScreen(viewModel = viewModel)
        }
        composable("programador_detail/{programadorId}") {
            val programadorId = it.arguments?.getInt("programadorId")
            if (programadorId != null) {
                val programador = viewModel.getProgramador(programadorId)
                ProgramadorDetails(programador)
            }
        }
    }
}