package com.projetopos.projetopos.resources;


import com.projetopos.projetopos.domain.Categoria;
import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.dto.CategoriaDTO;
import com.projetopos.projetopos.dto.ClienteDTO;
import com.projetopos.projetopos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping(value="/page")
    public ResponseEntity<Page<Cliente>> findPage(@RequestParam(value="page", defaultValue = "0") Integer page,
                                                       @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value="orderBy", defaultValue = "nome") String direction,
                                                       @RequestParam(value="direction", defaultValue = "ASC") String orderBy){
        Page<Cliente> clientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> clientesDTO= clientes.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
        Cliente obj = clienteService.fromDTO(objDTO);
        obj = clienteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
        Cliente obj = clienteService.fromDTO(objDTO);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
