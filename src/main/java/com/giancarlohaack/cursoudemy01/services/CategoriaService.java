package com.giancarlohaack.cursoudemy01.services;

import com.giancarlohaack.cursoudemy01.domain.Categoria;
import com.giancarlohaack.cursoudemy01.dto.CategoriaDTO;
import com.giancarlohaack.cursoudemy01.repositories.CategoriaRepository;
import com.giancarlohaack.cursoudemy01.services.exceptions.DataIntegrityException;
import com.giancarlohaack.cursoudemy01.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()
                )
        );
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null); //Segundo o professor, é pra garantir que sempre será um objeto novo.
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = find(obj.getId()); //Verificar se existe, se nao lança excecao
        updateData(newObj, obj);
        return categoriaRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDTO) {
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }

}
