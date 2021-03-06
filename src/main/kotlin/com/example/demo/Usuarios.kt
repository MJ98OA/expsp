package com.example.demo

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Usuarios(var nombre: String, var pass: String, var llave:String) {

    @Id
    @GeneratedValue
    var id = 0

    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }


    data class UsuarioSimple(var nombre: String, var pass: String) {

        override fun toString(): String {
            val gson = Gson()
            return gson.toJson(this)
        }

    }


}