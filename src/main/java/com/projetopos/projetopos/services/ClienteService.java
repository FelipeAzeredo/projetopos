package com.projetopos.projetopos.services;


import com.projetopos.projetopos.domain.Cidade;
import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.domain.Endereco;
import com.projetopos.projetopos.domain.TipoCliente;
import com.projetopos.projetopos.dto.ClienteDTO;
import com.projetopos.projetopos.dto.ClienteNewDTO;
import com.projetopos.projetopos.repositories.ClienteRepository;
import com.projetopos.projetopos.repositories.EnderecoRepository;
import com.projetopos.projetopos.services.exceptions.DataIntegrityException;
import com.projetopos.projetopos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj =  clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    @Transactional
    public Cliente create(Cliente obj){
        obj.setId(null);
        clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(obj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não e possivel excluir categorias que possui produtos");
        }
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){

        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  clienteRepository.findAll(pageRequest);

    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if(objDTO.getTelefone2() != null){
            cli.getTelefones().add(objDTO.getTelefone2());
        }
        if(objDTO.getTelefone3() != null){
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }







}
