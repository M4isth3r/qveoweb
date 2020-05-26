package com.qveo.qveoweb.dao;


import com.qveo.qveoweb.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeliculaDao extends JpaRepository<Pelicula, Integer> {
	//List<Pelicula> findByPlataformasIn(Collection<Plataforma> plataformas);
	Pelicula findTopByOrderByIdDesc();
}
