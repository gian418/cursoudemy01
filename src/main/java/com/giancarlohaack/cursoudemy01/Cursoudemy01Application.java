package com.giancarlohaack.cursoudemy01;

import com.giancarlohaack.cursoudemy01.domain.*;
import com.giancarlohaack.cursoudemy01.domain.enums.EstadoPagamento;
import com.giancarlohaack.cursoudemy01.domain.enums.TipoCliente;
import com.giancarlohaack.cursoudemy01.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Cursoudemy01Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Cursoudemy01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	
    }
}
