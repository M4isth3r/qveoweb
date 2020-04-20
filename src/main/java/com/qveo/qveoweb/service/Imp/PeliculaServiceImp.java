package com.qveo.qveoweb.service.Imp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.dao.PeliculaDao;
import com.qveo.qveoweb.model.Pelicula;
import com.qveo.qveoweb.service.PeliculaService;

@Service
public class PeliculaServiceImp implements PeliculaService{

	@Autowired
	PeliculaDao peliculaDao;
	// src/main/webapp/resources/img/series
	// resources/img/series
	public static String directorioPelicula="src/main/webapp/resources/img/pelicula/";
	
	public String rutaguardar="/resources/img/pelicula/";
	
	public String nombreFichero="";
	
	@Override
	public Optional<Pelicula> getPelicula(Integer id) {
				
		return peliculaDao.findById(id);
	}

	@Override
	public void save(Pelicula peliculaNew) throws IOException {
		
		
	peliculaNew.setPoster(rutaguardar+peliculaNew.getTitulo());
	
	peliculaDao.save(peliculaNew);	
	
	//nombreFichero="";
	}

	@Override
	public void saveImg(MultipartFile file) throws IOException {
		try {
			byte[] bytes= file.getBytes();
			nombreFichero=rutaguardar+file.getOriginalFilename();
					
			Path path= Paths.get(rutaguardar+file.getOriginalFilename());
			Files.write(path, bytes);
			
		}catch(NoSuchFieldError e) {
			System.err.println("Error de ficheros ---------------------------------");
			e.printStackTrace();
		}
		
	}

}