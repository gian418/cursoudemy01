package com.giancarlohaack.cursoudemy01.repositories;

import com.giancarlohaack.cursoudemy01.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {
}
