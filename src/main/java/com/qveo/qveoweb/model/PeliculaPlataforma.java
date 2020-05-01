package com.qveo.qveoweb.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

//@IdClass(PeliculaPlataformaId.class)
@Entity
public class PeliculaPlataforma {
	
	@EmbeddedId
	private PeliculaPlataformaId id = new PeliculaPlataformaId();
	
	
    @Column(name = "CADUCA")
	private Date fechaCaducidad;
	
	
	@ManyToOne
	@MapsId("plataformaId")
	@JoinColumn(name = "ID_PLATAFORMA")
	private Plataforma plataforma;
	
	
	@ManyToOne
	@MapsId("peliculaId")
	@JoinColumn(name = "ID_PELICULA")
	private Pelicula pelicula;
	
	
	public PeliculaPlataforma() {
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public PeliculaPlataformaId getId() {
		return id;
	}

	public void setId(PeliculaPlataformaId id) {
		this.id = id;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	
	
	
	
}
