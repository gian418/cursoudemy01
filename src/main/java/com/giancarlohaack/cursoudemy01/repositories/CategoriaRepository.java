package com.giancarlohaack.cursoudemy01.repositories;

import com.giancarlohaack.cursoudemy01.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
