package com.qveo.qveoweb.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import com.qveo.qveoweb.model.Director;
import com.qveo.qveoweb.model.Pais;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Genero;
import com.qveo.qveoweb.model.Pelicula;
import com.qveo.qveoweb.service.DirectorService;
import com.qveo.qveoweb.service.GeneroService;
import com.qveo.qveoweb.service.PaisService;
import com.qveo.qveoweb.service.PeliculaService;
import com.qveo.qveoweb.service.PlataformaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;

	@Autowired
	GeneroService generoService;

	@Autowired
	PaisService paisService;

	@Autowired
	PlataformaService plataformaSerive;

	@Autowired
	DirectorService directorService;

	@GetMapping("/{id}")
	public String home(Model mod, @PathVariable String id) {

		int idI = Integer.parseInt(id);

		Pelicula pelicula = peliculaService.getPelicula(idI).get();

		mod.addAttribute("peliculas", pelicula);

		return "pelicula";
	}

	@GetMapping("/form")
	public String PeliculaFormulario(Model mod) {
		List<Genero> peliculas = generoService.getAllGenero();
		List<Pais> paises = paisService.getAllPais();
		List<Plataforma> plataformas = plataformaSerive.getAllPlataformas();
		List<Director> directores = directorService.getAllDirector();

		mod.addAttribute("directores", directores);
		mod.addAttribute("peliculaNueva", new Pelicula());
		mod.addAttribute("paises", paises);
		mod.addAttribute("generos", peliculas);
		mod.addAttribute("plataformas", plataformas);

		return "registroPelicula";
	}

	@GetMapping("/form/add")
	public String PeliculaAdd(@Valid @ModelAttribute("PeliculaNueva") Pelicula peliculaN, BindingResult br,
			@RequestParam("poster") MultipartFile file, Model mod) {
		try {
			if (br.hasErrors()) {
				return "registroPelicula";
			}

			peliculaService.save(peliculaN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/listado";
	}

	@GetMapping("/ver")
	public String ver(Model mod, SessionStatus status) {

		return null;
	}
}
