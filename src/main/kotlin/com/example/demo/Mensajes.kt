package com.example.demo

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Mensajes(var texto: String, var usuarioId: String, var id:Int, var time:Int) {

    companion object{
        var list= mutableListOf<Mensajes>()
    }

    @Id
    @GeneratedValue
    var idm = 0

    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    data class MensajeSimple(var texto: String, var usuarioId: String, var id:Int) {

        override fun toString(): String {
            val gson = Gson()
            return gson.toJson(this)
        }

    }

}