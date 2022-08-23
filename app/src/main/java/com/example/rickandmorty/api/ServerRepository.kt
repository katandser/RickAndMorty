package com.example.rickandmorty.api

class ServerRepository(val api : IServerApi) {

    fun getAllEpisodes() = api.getAllEpisodes()
}