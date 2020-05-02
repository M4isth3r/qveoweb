package com.qveo.qveoweb.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qveo.qveoweb.model.AjaxResponseBody;
import com.qveo.qveoweb.model.SearchCriteria;
import com.qveo.qveoweb.model.Usuario;
import com.qveo.qveoweb.service.UsuarioService;

@RestController
public class SearchController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/ajax/usuario/{id}")
    public ResponseEntity<?> getSearchResultViaAjax(@PathVariable("id") Integer id) {

        AjaxResponseBody result = new AjaxResponseBody();

        //List<Usuario> users = usuarioService.findAllUsuarios();
        Usuario usuario = usuarioService.findById(id);
        
        if (usuario == null) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(usuario);

        return ResponseEntity.ok(usuario);

    }

}
