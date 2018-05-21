package com.giancarlohaack.cursoudemy01;

import com.giancarlohaack.cursoudemy01.domain.Categoria;
import com.giancarlohaack.cursoudemy01.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Cursoudemy01Application implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Cursoudemy01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
    }
}
