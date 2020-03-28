package com.projetopos.projetopos.services;

import com.projetopos.projetopos.domain.Categoria;
import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.repositories.CategoriaRepository;
import com.projetopos.projetopos.repositories.ClienteRepository;
import com.projetopos.projetopos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj =  clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }



}
