package com.qveo.qveoweb.model;

import javax.persistence.*;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Genero {
    private Integer id;
    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 5,max = 30, message = "Nombre genero debe ser entre 5 a 30 caracteres")
    private String nombre;
    private Collection<Pelicula> peliculas;
    private Collection<Serie> series;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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


    @ManyToMany
    @JoinTable(
            name ="genero_Pelicula",
            joinColumns = @JoinColumn(name = "id_genero", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_pelicula", nullable = false)
    )
    public Collection<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Collection<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @ManyToMany
    @JoinTable(
            name ="genero_serie",
            joinColumns = @JoinColumn(name = "id_genero", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_serie", nullable = false)
    )
    public Collection<Serie> getSeries() {
        return series;
    }

    public void setSeries(Collection<Serie> series) {
        this.series = series;
    }
}
