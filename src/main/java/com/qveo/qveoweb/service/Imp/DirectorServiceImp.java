package com.qveo.qveoweb.service.Imp;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.dao.DirectorDao;
import com.qveo.qveoweb.model.Director;
import com.qveo.qveoweb.service.DirectorService;
import com.qveo.qveoweb.service.UploadFileService;

@Service
public class DirectorServiceImp implements DirectorService {

	@Autowired
	private DirectorDao dirDao;

	@Autowired
	UploadFileService fotoService;

	@Override
	@Transactional(readOnly = true)
	public List<Director> getAllDirector() {

		return (List<Director>) dirDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Director getDirector(Integer id) {

		return dirDao.findById(id).orElse(null);
	}

	@Override
	public void save(Director director, MultipartFile foto) throws IOException {

		String fotoTemp;

		if (director.getId() != null) {
			fotoTemp = getDirector(director.getId()).getFoto();
		} else {
			fotoTemp = "";
		}
		
		dirDao.save(director);

		if (!foto.isEmpty()) {
			try {
				String uniqueFilename = null;

				uniqueFilename = fotoService.copy(foto, 4, director.getId(), director.getNombre());
				director.setFoto("/resources/img/directores/" + uniqueFilename);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (foto.isEmpty()) {
			String uniqueFilename = null;
			uniqueFilename = fotoService.defaultFoto(4, fotoTemp);
			director.setFoto("/resources/img/directores/" + uniqueFilename);

		}

		dirDao.save(director);

	}

	@Override
	@Transactional
	public void delete(Integer id) {

		Director director = getDirector(id);

		fotoService.delete(director.getFoto(), 4);

		dirDao.deleteById(id);

	}

}
