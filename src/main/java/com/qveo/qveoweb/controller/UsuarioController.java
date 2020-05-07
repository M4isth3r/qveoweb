package com.qveo.qveoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.validation.Valid;

import com.qveo.qveoweb.model.Pais;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Usuario;
import com.qveo.qveoweb.service.IUploadFileService;
import com.qveo.qveoweb.service.PaisService;
import com.qveo.qveoweb.service.PlataformaService;
import com.qveo.qveoweb.service.UsuarioService;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UsuarioController {

	private boolean editar = false;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PaisService paisService;

	@Autowired
	private PlataformaService plataformaService;

	@Autowired
	private IUploadFileService uploadFileService;

	@RequestMapping(value = "/usuario/list", method = RequestMethod.GET)
	public String listarUsuarios(Model modelo) {

		List<Usuario> usuarios = usuarioService.findAllUsuarios();

		modelo.addAttribute("usuarios", usuarios);

		return "usuario/lista";
	}

	@RequestMapping(value = "/usuario/form", method = RequestMethod.GET)
	public String mostrarFormulario(Model model) {
		editar=false;
		model.addAttribute("nuevoUsuario", new Usuario());

		List<Pais> paises = paisService.getAllPais();
		model.addAttribute("paises", paises);

		List<Plataforma> plataformas = plataformaService.getAllPlataformas();
		model.addAttribute("plataformas", plataformas);

		return "usuario/registro";
	}

	@RequestMapping(value = "/usuario/form/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("nuevoUsuario") Usuario usuario, BindingResult br,
			@RequestParam(value = "file") MultipartFile file, Model model) {
		try {
			if (br.hasErrors()) {
				model.addAttribute("nuevoUsuario", usuario);
				
				List<Pais> paises = paisService.getAllPais();
				model.addAttribute("paises", paises);

				List<Plataforma> plataformas = plataformaService.getAllPlataformas();
				model.addAttribute("plataformas", plataformas);

				return "usuario/registro";
			}

			
			
			if (!file.isEmpty()) {
				if(editar) {
					
					String rutaFoto = usuarioService.findById(usuario.getId()).getFoto();
					String ruta = rutaFoto.substring(rutaFoto.lastIndexOf('/') + 1);
	
					if (usuario.getId() != null && usuario.getId() > 0 && ruta != null && ruta.length() > 0) {
						uploadFileService.delete(ruta, 6);
					}
				
				}
				String uniqueFilename = uploadFileService.copy(file,6,usuario.getNombre());
				usuario.setFoto("/resources/img/usuarios/"+uniqueFilename);
			}
			
			if(file.isEmpty() && editar) {
				usuario.setFoto(usuarioService.findById(usuario.getId()).getFoto());
			}
			
			usuarioService.saveUser(usuario);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return "redirect:/usuario/list";
	}

	@RequestMapping(value = "/usuario/edit/{id}", method = RequestMethod.GET)
	public String editUsuario(Model modelo, @PathVariable("id") Integer id, Model model) {
		
		editar=true;
		Usuario usuario = usuarioService.findById(id);
		
		List<Pais> paises = paisService.getAllPais();
		model.addAttribute("paises", paises);

		List<Plataforma> plataformas = plataformaService.getAllPlataformas();
		model.addAttribute("plataformas", plataformas);

		modelo.addAttribute("nuevoUsuario", usuario);
		modelo.addAttribute("edit", editar);
		
		return "usuario/registro";
	}

}
