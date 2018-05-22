package com.giancarlohaack.cursoudemy01.repositories;

import com.giancarlohaack.cursoudemy01.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
