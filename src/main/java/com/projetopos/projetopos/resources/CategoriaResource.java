package com.projetopos.projetopos.resources;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.projetopos.projetopos.domain.Categoria;
import com.projetopos.projetopos.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value="/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){

        Categoria categoria = categoriaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }


}

