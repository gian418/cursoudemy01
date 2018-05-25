package com.giancarlohaack.cursoudemy01.resources;

import com.giancarlohaack.cursoudemy01.domain.Categoria;
import com.giancarlohaack.cursoudemy01.domain.Produto;
import com.giancarlohaack.cursoudemy01.dto.CategoriaDTO;
import com.giancarlohaack.cursoudemy01.dto.ProdutoDTO;
import com.giancarlohaack.cursoudemy01.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable Integer id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") Integer categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {

        Page<Produto> list = service.search(page, linesPerPage, orderBy, direction);

        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
