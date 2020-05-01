package com.qveo.qveoweb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.qveo.qveoweb.model.Actor;

public interface ActorService {

	List<Actor> findAllActor();
	void save(Actor actorNuevo);
	void deleteActor(Integer id);
	Optional<Actor> getActor(Integer id);
}
