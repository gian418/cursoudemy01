package com.giancarlohaack.cursoudemy01.repositories;

import com.giancarlohaack.cursoudemy01.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
