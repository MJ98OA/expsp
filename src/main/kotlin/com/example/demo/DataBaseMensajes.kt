package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean

class DataBaseMensajes {

    @Bean
    fun initDatabase(dataBaseMensajes: DataBaseMensajes): CommandLineRunner {
        return CommandLineRunner {

        }
    }
}
