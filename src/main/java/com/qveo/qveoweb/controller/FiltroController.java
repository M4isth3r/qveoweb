package com.qveo.qveoweb.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qveo.qveoweb.dto.FiltroDto;
import com.qveo.qveoweb.model.Genero;
import com.qveo.qveoweb.model.Pelicula;
import com.qveo.qveoweb.model.PeliculaPlataforma;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Serie;
import com.qveo.qveoweb.service.FiltroService;
import com.qveo.qveoweb.service.GeneroService;
import com.qveo.qveoweb.service.PeliculaPlataformaService;
import com.qveo.qveoweb.service.PeliculaService;
import com.qveo.qveoweb.service.PlataformaService;
import com.qveo.qveoweb.service.SerieService;

@Controller
public class FiltroController {

	@Autowired
	SerieService serieService;

	@Autowired
	GeneroService generoService;

	@Autowired
	PeliculaService peliculaService;

	@Autowired
	PlataformaService plataformaSerice;

	@Autowired
	PeliculaPlataformaService peliPlataService;
	
	@Autowired
	FiltroService filtroService;

	/*
	 * Get controller para series
	 */
	@GetMapping("/filtro1")
	public String buscadorSerie(Model model) {

		model.addAttribute("seriesBuscar", new FiltroDto());

		List<Genero> generos = generoService.getAllGenero();

		List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();

		List<Serie> series = serieService.findAllSerie();

		List<Integer> fecha = filtroService.buscarAllYears();
		
		model.addAttribute("fechas", fecha);
		model.addAttribute("plataformas", plataformas);
		model.addAttribute("serieMostrar", series);

		model.addAttribute("generos", generos);

		return "filtros/filtro";
	}

	/*
	 * Post controler para series
	 */
	@PostMapping("/filtros")
	public String filtrar(@ModelAttribute(name = "seriesBuscar") FiltroDto filtro, Model model, BindingResult br) {

		if (br.hasErrors()) {

			model.addAttribute("seriesBuscar", filtro);

			List<Genero> generos = generoService.getAllGenero();

			List<Serie> series = serieService.findAllSerie();

			List<Integer> fecha = filtroService.buscarAllYears();

			model.addAttribute("fechas", fecha);
			model.addAttribute("serieMostrar", series);

			model.addAttribute("generos", generos);

			return "filtros/filtro";
		}
			
		 //List<Serie> series =filtroService.busquedaCompletaSerie(filtro.getGeneros(),filtro.getPlataformas(), filtro.getAnios());
		 
		List<Pelicula> peli= filtroService.busquedaCompletaPelicula(filtro.getGeneros(), filtro.getAnios(),filtro.getPlataformas());
		
		for(Pelicula pelisc:peli) {
			System.out.println(pelisc.getTitulo()+" "+pelisc.getDuracion());
		}
		System.out.println("");
		// model.addAttribute("series", series);
		
		return "filtros/filtrado";
	}

	/*
	 * Get controller para pelicula
	 */
	@GetMapping("/filtro2")
	public String buscadorPeli(Model model) {

		 model.addAttribute("peliculaBuscar", new Pelicula());
		List<Genero> generos = generoService.getAllGenero();
		List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();
		List<Pelicula> peliculas = peliculaService.findAll();

		//model.addAttribute("peliculaBuscar", new PeliculaPlataforma());
		model.addAttribute("plataformas", plataformas);
		model.addAttribute("generos", generos);
		model.addAttribute("peliculaMostrar", peliculas);

		return "filtros/filtro2";
	}

	/*
	 * Post controler para series
	 */
	@PostMapping("/filtrosP")
	public String filtrar(@ModelAttribute(name = "peliculaBuscar") Pelicula pelicula,
			@RequestParam(name = "years", required = false) List<Integer> dates,@RequestParam(name = "platafa", required = false) Collection<Plataforma> plataforma ,Model model, BindingResult br) {
		
		if (br.hasErrors()) {

			List<Genero> generos = generoService.getAllGenero();
			List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();
			List<Pelicula> peliculas = peliculaService.findAll();

			model.addAttribute("peliculaBuscar", new PeliculaPlataforma());
			model.addAttribute("plataformas", plataformas);
			model.addAttribute("generos", generos);
			model.addAttribute("peliculaMostrar", peliculas);

			return "filtros/filtro2";
		}
		 
		// System.out.println(pelicula.getPeliculaPlataformas().isEmpty());

		// --->Filtro de pelicula por genero
		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGenero(pelicula.getPeliculas());

		// --->Filtro de pelicula por genero
		//List<Pelicula>	peliculas=peliculaService.buscarPeliGeneroYears(peliculaPlataforma.getPelicula().getGeneros(), dates);

		// --->Filtro de pelicula por genero y años
		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGeneroYears(pelicula.getPeliculas(),
		// dates);

		// -------->Filtrar por plataforma
		// List<Pelicula> peliculas = peliculaService.buscarPeliPlataforma(peliculaPlataforma);

		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGenero(peliculaPlataforma.getPelicula().getGeneros());
		//List<Pelicula> peliculas = peliculaService.buscarPeliYears(dates);
		/*if (peliculas.isEmpty()) {
			System.out.println("Lista vacia");
		} else {
			System.out.println("No esta vacia");
		}*/
		// model.addAttribute("peliculas", peliculas);

		//List<Pelicula> pelis=filtroService.busquedaCompletaPelicula(peliculaPlataforma.getPelicula().getGeneros(), dates);
	
		return "filtros/filtradoP";
	}
}
