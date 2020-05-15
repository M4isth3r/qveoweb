package com.qveo.qveoweb.service;

import com.qveo.qveoweb.model.Pelicula;
import java.util.List;



public interface PeliculaService {
	
    //Map<Plataforma, List<Pelicula>> getPeliculaForCarousel();
	
	List<Pelicula> findAll();

}