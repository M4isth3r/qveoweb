package com.qveo.qveoweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.model.Usuario;

public interface UsuarioService {

	List<Usuario> findAllUsuarios();
    public void saveUser(Usuario usuario);
    public Usuario findById(Integer id);
    public void editUser(Usuario usuario);
    public void deleteUser(Integer id);
    public void saveImg(MultipartFile file, Usuario usuario, boolean actFile) throws IOException;
    public List<Usuario> findUsuarioPorNombre(String nombre);
    public boolean usuarioExiste(Integer id);
}