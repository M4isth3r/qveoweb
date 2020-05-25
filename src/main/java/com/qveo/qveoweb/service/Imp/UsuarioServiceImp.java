package com.qveo.qveoweb.service.Imp;

import java.io.IOException;
import java.util.List;

import com.qveo.qveoweb.dao.RolDao;
import com.qveo.qveoweb.dao.UsuarioDao;
import com.qveo.qveoweb.model.Rol;
import com.qveo.qveoweb.model.Usuario;
import com.qveo.qveoweb.service.UploadFileService;
import com.qveo.qveoweb.service.UsuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    RolDao rolDao;
    
    @Autowired
	UploadFileService uploadFileService;

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioDao.findAll();
    }

    public void saveUser(Usuario usuario, MultipartFile foto) throws IOException {

        Rol rol = rolDao.findById(1);
        usuario.setRol(rol);
        
    	String fotoTemp;

		if (usuario.getId() != null) {
			fotoTemp = getUsuario(usuario.getId()).getFoto();
			
		} else {
			fotoTemp = "";
		}
        usuarioDao.save(usuario);

		if (!foto.isEmpty()) {
			try {
				String uniqueFilename = null;

				uniqueFilename = uploadFileService.copy(foto, 6, usuario.getId(), usuario.getNombre());
				usuario.setFoto("/resources/img/usuarios/" + uniqueFilename);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (foto.isEmpty()) {
			String uniqueFilename = null;
			uniqueFilename = uploadFileService.defaultFoto(6, fotoTemp);
			usuario.setFoto("/resources/img/usuarios/" + uniqueFilename);

		}

		usuarioDao.save(usuario);
    }

    @Override
    public Usuario getUsuario(Integer id) {

        Usuario entity = usuarioDao.findById(id).get();
        return entity;
    }

    @Override
    public void deleteUser(Integer id) {
        usuarioDao.deleteById(id);

    }

	@Override
	public List<Usuario> findUsuarioPorNombre(String nombre) {
		
		List<Usuario> usuarios = usuarioDao.findByNombreStartingWith(nombre);
		
		return usuarios;
	}

	@Override
	public boolean usuarioExiste(Integer id) {
		
		return usuarioDao.existsById(id);
	}
    

}