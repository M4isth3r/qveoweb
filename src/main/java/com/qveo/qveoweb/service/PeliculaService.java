package com.qveo.qveoweb.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.model.Pelicula;

public interface PeliculaService {
	Optional<Pelicula> getPelicula(Integer id);
	public void save(Pelicula PeliculaNew) throws IOException;
	public void saveImg(MultipartFile file) throws IOException;
	
}