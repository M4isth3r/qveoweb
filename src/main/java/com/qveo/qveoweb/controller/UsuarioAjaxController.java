package com.qveo.qveoweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qveo.qveoweb.model.AjaxResponseBody;
import com.qveo.qveoweb.model.Usuario;
import com.qveo.qveoweb.service.UsuarioService;

@RestController
public class UsuarioAjaxController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/ajax/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSearchResultViaAjax(@PathVariable("id") Integer id) {

        //AjaxResponseBody result = new AjaxResponseBody();

        Usuario usuario = usuarioService.findById(id);

        return ResponseEntity.ok(usuario);

    }
	
	@RequestMapping(value = "/ajax/usuario/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {

		//AjaxResponseBody result = new AjaxResponseBody();
		
		//if(usuarioService.usuarioExiste(id)) {
			
			Usuario usuario = usuarioService.findById(id);
			
			usuarioService.deleteUser(id);
		//}

		return ResponseEntity.ok(usuario);

	}
	
	@RequestMapping(value = "/ajax/usuarios/", method = RequestMethod.POST)
	public ResponseEntity<?> getUsuariosPorNombre(@RequestBody Usuario usuario) {

		//AjaxResponseBody result = new AjaxResponseBody();

		List<Usuario> usuarios = usuarioService.findUsuarioPorNombre(usuario.getNombre());

		return ResponseEntity.ok(usuarios);

	}

}
