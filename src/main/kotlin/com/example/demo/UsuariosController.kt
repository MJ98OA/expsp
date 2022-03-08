package com.example.demo

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
class UsuariosController(private val usuariosRepository: UsuariosRepository,private val mensajesRepository: MensajesRepository) {


    companion object{
        val timeC: Long =123213123213
    }

    @PostMapping("crearUsuario")
    fun creacionUsuario(@RequestBody usuarioSimple: Usuarios.UsuarioSimple):String  {
        var llavecifrado=obtenerLlaveDeCifrado()
        var usuarioNuevo=Usuarios(usuarioSimple.nombre,usuarioSimple.pass,llavecifrado)

        var crear=0
        var devolver=""
        usuariosRepository.findAll().forEach { if (it.nombre==usuarioNuevo.nombre) crear++ }
        if(llavecifrado.length<20 || llavecifrado.length>20) crear++
        if(crear==0){
            usuariosRepository.save(usuarioNuevo)
            devolver=usuarioNuevo.llave
        }else{
            devolver="{\"codigo\":1,\"motivo\":\"Pass invalida\"}"
        }
        return devolver
    }


    @PostMapping("crearMensaje")
    fun crearMensaje(@RequestBody mensajes: Mensajes.MensajeSimple):String {

        var obtenerUsuario=mensajes.usuarioId
        var usuario=buscarUsuario(obtenerUsuario)
        var devolver="{\"codigo\":2,\"motivo\":\"Usuario inexistente\"}"
        usuario?.let {
            mensajesRepository.save(Mensajes(mensajes.texto,mensajes.usuarioId,mensajes.id, timeC.toInt()))
            devolver="Success"
        }

        return devolver
    }

    @GetMapping("descargarMensajes")
    fun descargarMensajes(@RequestBody mensajes: Mensajes) : String {
        var list= mutableListOf<Mensajes>()
        mensajesRepository.findAll().forEach { list.add(it) }
        return list.toString()
    }


    @GetMapping("obtenerMensajesYLlaves")
    fun insertStudent(@RequestBody usuarioSimple: Usuarios.UsuarioSimple): String {
        var comprobarUsuarioAdmin=false
        var lista= mutableListOf<Any>()
        var devolver=""
        usuariosRepository.findAll().forEach { println(it) }
        usuariosRepository.findAll().forEach { if(it.nombre==usuarioSimple.nombre && it.pass==usuarioSimple.pass) comprobarUsuarioAdmin=true }
        if(comprobarUsuarioAdmin){
            mensajesRepository.findAll().forEach { it1->
                usuariosRepository.findAll().forEach{
                    if(it1.usuarioId==it.nombre)
                        lista.add(it1.toString()+it.llave)
                }
            }
            devolver=lista.toString()
        }else
            devolver="{\"codigo\":3,\"motivo\":\"Pass de administrador incorrecta\"}"

        return devolver
    }

    fun obtenerLlaveDeCifrado(): String {
        var resultado = ""
        repeat(20) {
            val num = Random.nextInt(10)
            resultado += num
        }
        return resultado
    }

    fun buscarUsuario(usuario:String): Usuarios? {
        var usuarioEncontrado: Usuarios? =null
        usuariosRepository.findAll().forEach { if(it.nombre==usuario) usuarioEncontrado=it }
        return usuarioEncontrado
    }
}