package com.projetopos.projetopos.services;

import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.domain.Pedido;
import com.projetopos.projetopos.repositories.ClienteRepository;
import com.projetopos.projetopos.repositories.PedidoRepository;
import com.projetopos.projetopos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj =  pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
