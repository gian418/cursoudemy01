package com.giancarlohaack.cursoudemy01.repositories;

import com.giancarlohaack.cursoudemy01.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
