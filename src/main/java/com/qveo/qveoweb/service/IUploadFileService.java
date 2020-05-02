package com.qveo.qveoweb.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public String copy(MultipartFile file,Integer accion,String titulo) throws IOException;

	public boolean delete(String filename,Integer accion);
}