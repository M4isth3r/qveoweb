package com.qveo.qveoweb.service;

import java.util.Collection;
import java.util.List;
import com.qveo.qveoweb.model.Genero;
import com.qveo.qveoweb.model.Pelicula;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Serie;


public interface FiltroService {
	List<Integer> buscarAllYears();
	List<Serie> busquedaCompletaSerie(Collection<Genero> generos,Collection<Plataforma> plataforma,Collection<Integer> years);
	List<Pelicula> busquedaCompletaPelicula(Collection<Genero> generos,Collection<Integer> years,Collection<Plataforma> plataforma);
}