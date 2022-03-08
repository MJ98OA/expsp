package com.example.demo
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataBaseUsuarios {

    @Bean
    fun initDatabase(usuariosRepository: UsuariosRepository): CommandLineRunner {
        return CommandLineRunner {
            val p = Usuarios("DAM2","123456","213123123")
            usuariosRepository.save(p)

            usuariosRepository.findAll().forEach{
                println(it)
            }

        }
    }
}
