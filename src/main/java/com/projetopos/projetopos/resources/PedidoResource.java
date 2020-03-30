package com.projetopos.projetopos.resources;

import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.domain.Pedido;
import com.projetopos.projetopos.services.ClienteService;
import com.projetopos.projetopos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Integer id){

        Pedido pedido = pedidoService.find(id);
        return ResponseEntity.ok().body(pedido);
    }
}
