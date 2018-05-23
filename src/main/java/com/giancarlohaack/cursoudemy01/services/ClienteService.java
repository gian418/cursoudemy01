package com.giancarlohaack.cursoudemy01.services;

import com.giancarlohaack.cursoudemy01.domain.Cliente;
import com.giancarlohaack.cursoudemy01.domain.Cliente;
import com.giancarlohaack.cursoudemy01.dto.ClienteDTO;
import com.giancarlohaack.cursoudemy01.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()
                )
        );
    }

//    public Cliente insert(Cliente obj) {
//        obj.setId(null); //Segundo o professor, é pra garantir que sempre será um objeto novo.
//        return ClienteRepository.save(obj);
//    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId()); //Verificar se existe, se nao lança excecao
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um cliente que possua pedidos");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
