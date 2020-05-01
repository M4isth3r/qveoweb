package com.qveo.qveoweb.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Plataforma {
    private Integer id;
    private String nombre;
    private Collection<Serie> series;
    private Collection<Usuario> usuarios;
    @OneToMany(mappedBy = "plataforma")
    private List<PeliculaPlataforma> peliculaPlataformas = new ArrayList<>();

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<PeliculaPlataforma> getPeliculaPlataformas() {
		return peliculaPlataformas;
	}

	public void setPeliculaPlataformas(List<PeliculaPlataforma> peliculaPlataformas) {
		this.peliculaPlataformas = peliculaPlataformas;
	}

	@ManyToMany
    @JoinTable(
            name ="serie_plataforma",
            joinColumns = @JoinColumn(name = "id_plataforma", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_serie", nullable = false)
    )
    public Collection<Serie> getSeries() {
        return series;
    }

    public void setSeries(Collection<Serie> series) {
        this.series = series;
    }
    
    @ManyToMany(mappedBy = "plataformas")
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
