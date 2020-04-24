package com.qveo.qveoweb.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.model.Serie;

public interface SerieService {
	Optional<Serie> getSerie(Integer id);
	public void save(Serie serieNew);
	public void saveImg(MultipartFile file,String titulo,boolean actFile) throws IOException;
	public void editarSerie(Serie serieAct,boolean actPoster,String extension);
	public String obtenerExtension(MultipartFile file);
	List<Serie> findAllSerie();
	void deleteSerie(Integer id);
	
}
