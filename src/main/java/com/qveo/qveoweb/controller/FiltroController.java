package com.qveo.qveoweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qveo.qveoweb.model.Genero;
import com.qveo.qveoweb.model.Pelicula;
import com.qveo.qveoweb.model.PeliculaPlataforma;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Serie;
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

	/*
	 * Get controller para series
	 */
	@GetMapping("/filtro1")
	public String buscadorSerie(Model model) {

		model.addAttribute("seriesBuscar", new Serie());

		List<Genero> generos = generoService.getAllGenero();

		List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();

		List<Serie> series = serieService.findAllSerie();

		List<Integer> fecha = serieService.buscarAllYears();

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
	public String filtrar(@ModelAttribute(name = "seriesBuscar") Serie serie,
			@RequestParam(name = "years", required = false) Integer[] dates, Model model, BindingResult br) {

		if (br.hasErrors()) {

			model.addAttribute("seriesBuscar", new Serie());

			List<Genero> generos = generoService.getAllGenero();

			List<Serie> series = serieService.findAllSerie();

			List<Integer> fecha = serieService.buscarAllYears();

			model.addAttribute("fechas", fecha);
			model.addAttribute("serieMostrar", series);

			model.addAttribute("generos", generos);

			return "filtros/filtro";
		}
		
		/*Collection<Genero> genero=null;
		if(serie.getGeneros().isEmpty()) {
			genero=generoService.getAllGeneros();
		}else {
			genero=serie.getGeneros();
		}
		
		Collection<Plataforma> plataforma=null;
		
		if(serie.getPlataformas().isEmpty()) {
			plataforma=plataformaSerice.getAllPlataformas();
		}else {
			plataforma=serie.getPlataformas();
		}
		List<Integer> fecha = null;
		if(dates == null) {
			fecha=serieService.buscarAllYears();
		}else {
			fecha=dates;
		}
		
		 List<Serie> series =serieService.busquedaCompletaSerie(genero,plataforma, fecha);*/
		// model.addAttribute("series", series);
		
		/*if (!serie.getGeneros().isEmpty() && !serie.getPlataformas().isEmpty() && dates != null) {
			/// --------->Filtrar completo
			// List<Serie> series =serieService.busquedaCompletaSerie(serie.getGeneros(),
			/// serie.getPlataformas(), dates);
			// model.addAttribute("series", series);
			model.addAttribute("cartel", "Entras si no esta vacio");
		} else {

			if (!serie.getGeneros().isEmpty() && dates != null && serie.getPlataformas().isEmpty()) {

				// -------->Filtrar por años y generos
				// List<Serie> series = serieService.busquedaGeneroYears(dates,
				// serie.getGeneros());
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si plataforma vacio");
			} else if (!serie.getGeneros().isEmpty() && dates == null && !serie.getPlataformas().isEmpty()) {

				// --------->Filtrar por plataforma y genero
				// List<Serie> series
				// =serieService.buscarGeneroPlataforma(serie.getPlataformas(),
				// serie.getGeneros());
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si fecha esta vacio");
			} else if (serie.getGeneros().isEmpty() && dates != null && !serie.getPlataformas().isEmpty()) {

				// --------->Filtrar por plataforma y años
				// List<Serie> series
				// =serieService.buscarPlataformaYears(serie.getPlataformas(), dates);
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si genero esta vacio");
			} else if (dates != null && serie.getGeneros().isEmpty() && serie.getPlataformas().isEmpty()) {
				// ------->Filtrar por años
				// List<Serie> series = serieService.buscarPorYear(dates);
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si genero y plataforma esta vacio");
			} else if (dates == null && !serie.getGeneros().isEmpty() && serie.getPlataformas().isEmpty()) {
				// ----->Filtrar por genero
				// List<Serie> series = serieService.buscarSerie(serie.getGeneros());
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si fecha y plataforma esta vacio");
			} else if (dates == null && serie.getGeneros().isEmpty() && !serie.getPlataformas().isEmpty()) {
				// -------->Filtrar por plataforma
				// List<Serie> series =serieService.buscarPlataforma(serie.getPlataformas());
				// model.addAttribute("series", series);
				model.addAttribute("cartel", "Entras si genero y fecha esta vacio");
			} else {
				return "redirect:/filtro1";
			}

		}*/

		// ----->Filtrar por genero
		// List<Serie> series = serieService.buscarSerie(serie.getGeneros());

		// ------->Filtrar por años
		// List<Serie> series = serieService.buscarPorYear(dates);

		// -------->Filtrar por plataforma
		// List<Serie> series =serieService.buscarPlataforma(serie.getPlataformas());

		// -------->Filtrar por años y generos
		// List<Serie> series = serieService.busquedaGeneroYears(dates,
		// serie.getGeneros());

		/// --------->Filtrar por plataforma y genero
		// List<Serie> series
		/// =serieService.buscarGeneroPlataforma(serie.getPlataformas(),
		/// serie.getGeneros());

		/// --------->Filtrar por plataforma y años
		// List<Serie> series
		/// =serieService.buscarPlataformaYears(serie.getPlataformas(), dates);

		/// --------->Filtrar completo
		// List<Serie> series =serieService.busquedaCompletaSerie(serie.getGeneros(),
		/// serie.getPlataformas(), dates);

		// model.addAttribute("series", series);
		// model.addAttribute("cartel", "Entras");

		return "filtros/filtrado";
	}

	/*
	 * Get controller para pelicula
	 */
	@GetMapping("/filtro2")
	public String buscadorPeli(Model model) {

		// model.addAttribute("peliculaBuscar", new Pelicula());
		// List<Genero> generos = generoService.getAllGeneros();
		List<Integer> fecha = peliculaService.buscarAllYears();
		List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();
		List<Pelicula> peliculas = peliculaService.findAll();

		model.addAttribute("peliculaBuscar", new PeliculaPlataforma());
		model.addAttribute("fechas", fecha);
		model.addAttribute("plataformas", plataformas);
		// model.addAttribute("generos", generos);
		model.addAttribute("peliculaMostrar", peliculas);

		return "filtros/filtro2";
	}

	/*
	 * Post controler para series
	 */
	@PostMapping("/filtrosP")
	public String filtrar(@ModelAttribute(name = "peliculaBuscar") PeliculaPlataforma peliculaPlataforma,
			@RequestParam(name = "years", required = false) String[] dates, Model model, BindingResult br) {
		/*
		 * if (br.hasErrors()) {
		 * 
		 * model.addAttribute("peliculaBuscar", new Pelicula()); List<Genero> generos =
		 * generoService.getAllGeneros(); List<Integer> fecha =
		 * peliculaService.buscarAllYears(); model.addAttribute("fechas", fecha);
		 * 
		 * List<Plataforma> plataformas = plataformaSerice.getAllPlataformas();
		 * 
		 * model.addAttribute("plataformas", plataformas); model.addAttribute("generos",
		 * generos);
		 * 
		 * List<Pelicula> peliculas = peliculaService.findAll();
		 * model.addAttribute("peliculaMostrar", peliculas);
		 * 
		 * return "filtros/filtro"; }
		 */
		// System.out.println(pelicula.getPeliculaPlataformas().isEmpty());

		// --->Filtro de pelicula por genero
		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGenero(pelicula.getPeliculas());

		// --->Filtro de pelicula por genero
		//

		// --->Filtro de pelicula por genero y años
		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGeneroYears(pelicula.getPeliculas(),
		// dates);

		// -------->Filtrar por plataforma
		// List<Pelicula> peliculas =
		// peliculaService.buscarPeliPlataforma(pelicula.getPeliculaPlataformas());

		//

		// List<Pelicula>
		// peliculas=peliculaService.buscarPeliGenero(peliculaPlataforma.getPelicula().getGeneros());
		List<Pelicula> peliculas = peliculaService.buscarPeliYears(dates);
		if (peliculas.isEmpty()) {
			System.out.println("Lista vacia");
		} else {
			System.out.println("No esta vacia");
		}
		// model.addAttribute("peliculas", peliculas);

		model.addAttribute("peliculas", peliculas);
		return "filtros/filtradoP";
	}
}
