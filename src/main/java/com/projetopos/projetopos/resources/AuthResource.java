package com.projetopos.projetopos.resources;

import com.projetopos.projetopos.dto.EmailDTO;
import com.projetopos.projetopos.security.JWTUtil;
import com.projetopos.projetopos.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetopos.projetopos.security.UserSS;
import com.projetopos.projetopos.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping(value="/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headres", "Authorization");
        return ResponseEntity.noContent().build();
    }
    /*
    @PostMapping(value="/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO){
        service.sendNewPassword(objDTO.getEmail());
        return ResponseEntity.noContent().build();
    }*/

}
