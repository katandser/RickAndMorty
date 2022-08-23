package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.api.ServerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseViewModel : ViewModel(), KoinComponent {

    protected val server : ServerRepository by inject()
}